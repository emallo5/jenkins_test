package com.aparcsystems.ui.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.aparcsystems.R;
import com.aparcsystems.client.AndroidClient;
import com.aparcsystems.dialog.OnAcceptListener;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.task.RestAsyncTask;
import com.aparcsystems.task.ShowPenaltyTask;
import com.aparcsystems.task.event.OnApiErrorEvent;
import com.aparcsystems.task.event.OnConnectionErrorEvent;
import com.aparcsystems.ui.activity.InputActivity;
import com.aparcsystems.ui.activity.commons.BaseActivity;
import com.aparcsystems.utils.CameraPreview;
import com.google.inject.Inject;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.lang.ref.WeakReference;

public class ScanInputFragment extends BaseInputFragment {
    private FrameLayout preview;
    private OnAcceptListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listener=new OnAcceptListener() {
            @Override
            public void onAccept(String dialogTag) {
               initCamera();
            }
        };
		return inflater.inflate(R.layout.scan_input_fragment, container, false);
	}

	private Camera mCamera;
	private CameraPreview mPreview;
	private Handler autoFocusHandler;

	ImageScanner scanner;

	private boolean barcodeScanned = false;
	private boolean previewing = true;

	static {
		System.loadLibrary("iconv");
	}

	public void onPause() {
		super.onPause();
		releaseCamera();
	}

    @Override
    public void onResume() {
        super.onResume();
        if(mCamera==null){
            initCamera();
        }
    }

    private void initCamera() {
        barcodeScanned=false;
        previewing=true;
        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(getActivity(), mCamera, previewCb,
                autoFocusCB);
        preview.removeAllViews();
        preview.addView(mPreview);
    }

    /** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {
		}
		return c;
	}

	private void releaseCamera() {
		if (mCamera != null) {
			previewing = false;
            mPreview.getHolder().removeCallback(mPreview);
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}

	private Runnable doAutoFocus = new Runnable() {
		public void run() {
			if (previewing)
				mCamera.autoFocus(autoFocusCB);
		}
	};

	PreviewCallback previewCb = new PreviewCallback() {
		public void onPreviewFrame(byte[] data, Camera camera) {
			Camera.Parameters parameters = camera.getParameters();
			Size size = parameters.getPreviewSize();

			Image barcode = new Image(size.width, size.height, "Y800");
			barcode.setData(data);

			int result = scanner.scanImage(barcode);

			if (result != 0 && isBarCode()) {
				previewing = false;
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();

				SymbolSet syms = scanner.getResults();
				for (Symbol sym : syms) {
                    releaseCamera();
                    onReceivePenaltyCode(sym.getData());
					barcodeScanned = true;
				}
			}
		}
	};

	private boolean isBarCode() {
            for (Symbol sym : scanner.getResults()) {
                return sym.getType() == Symbol.CODE39;
            }
		return true;
	}

	// Mimic continuous auto-focusing
	AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			autoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};

	@Override
	public void onViewCreated(final View view,
			@Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();


		/* Instance barcode scanner */
        scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);
        preview = (FrameLayout) view
                .findViewById(R.id.cameraPreview);

        initCamera();
	}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.enter_manually).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputActivity.startActivity(getActivity(), ManualInputFragment.class.getName());
            }
        });
    }

    public void onEvent(OnApiErrorEvent event){
        ParktoriaDialog parktoriaDialog=new ParktoriaDialog(getActivity().getString(R.string.scan_citation_number_error));
        parktoriaDialog.setTitleResId(R.string.error);
        parktoriaDialog.setCancelable(false);
        parktoriaDialog.setOnAcceptListener(listener);
        parktoriaDialog.show(((BaseActivity) getActivity()).getSupportFragmentManager());
        dismissFragmentLoadingOnUIThread();
    }

    public void onEvent(OnConnectionErrorEvent event){
        super.onEvent(event);
        parktoriaConnectionErrorDialog.setOnAcceptListener(listener);
    }
}
