package com.aparcsystems.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aparcsystems.R;
import com.aparcsystems.dialog.OnAcceptListener;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.mock.PenaltyMocks;
import com.aparcsystems.model.Penalty;
import com.aparcsystems.task.ShowPenaltyTask;
import com.aparcsystems.task.event.OnApiErrorEvent;
import com.aparcsystems.task.event.OnConnectionErrorEvent;
import com.aparcsystems.task.event.OnErrorEvent;
import com.aparcsystems.ui.activity.SuccessActivity;
import com.aparcsystems.ui.activity.ShowPenaltyActivity;
import com.aparcsystems.ui.activity.commons.BaseActivity;
import com.aparcsystems.utils.StringUtils;
import com.aparcsystems.utils.ValidationUtils;

import roboguice.inject.InjectView;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class ManualInputFragment extends BaseInputFragment {
    @InjectView(R.id.barcode)
    private EditText barcode;
    @InjectView(R.id.enter_bar_code)
    private Button enterBarCode;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_input_fragment,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        enterBarCode.setEnabled(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        barcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    onProcessInputAction();
                }
                return false;
            }
        });
        enterBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProcessInputAction();
            }
        });
        barcode.setEnabled(true);

        getView().findViewById(R.id.enter_bar_code).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ShowPenaltyActivity.startActivity(getActivity(), PenaltyMocks.getPaidPenalty());
                return false;
            }
        });
    }

    public void onProcessInputAction(){
        enterBarCode.setEnabled(false);
        if (StringUtils.isEmpty(barcode.getText().toString())) {
            barcode.setError(getActivity().getString(R.string.citation_number_required));
            barcode.setOnKeyListener(new View.OnKeyListener() {
                boolean firstTime=true;
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (StringUtils.isEmpty(barcode.getText().toString())) {
                        barcode.setError(getActivity().getString(R.string.citation_number_required));
                    }else{
                        barcode.setError(null);
                    }
                    return false;
                }
            });
            enterBarCode.setEnabled(true);
            return;
        }
        onReceivePenaltyCode(barcode.getText().toString());
    }
    public void onEvent(OnConnectionErrorEvent event){
        super.onEvent(event);
        enterBarCode.setEnabled(true);
    }

    public void onEvent(OnApiErrorEvent event){
        handleApiError(R.string.manual_citation_number_error);
        enterBarCode.setEnabled(true);
    }
}
