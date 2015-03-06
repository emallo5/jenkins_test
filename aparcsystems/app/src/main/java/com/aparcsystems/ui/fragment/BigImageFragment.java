package com.aparcsystems.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aparcsystems.R;
import com.aparcsystems.model.Photo;
import com.aparcsystems.utils.AndroidScreenUtils;
import com.aparcsystems.utils.BitmapUtils;
import com.aparcsystems.utils.ExtraConstants;

import roboguice.inject.InjectView;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class BigImageFragment extends BaseFragment {
    @InjectView(R.id.big_image_preview)
    private ImageView bigImagePreview;
    @InjectView(R.id.delete_image)
    private ImageView deleteImage;
    private Photo photo;
    private int position;
    private ParktoriaTwoOptions areYouSureDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.big_image_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photo= (Photo) getArguments().getSerializable(ExtraConstants.PHOTO_EXTRA);
        position=getArguments().getInt(ExtraConstants.PHOTO_POSITION_EXTRA);
        BitmapUtils.loadBitmapInListBackground(getActivity(),bigImagePreview.getId(),bigImagePreview,AndroidScreenUtils.getDisplayScreen(getActivity()).x, AndroidScreenUtils.getDisplayScreen(getActivity()).y, photo,R.drawable.placeholder,R.color.place_holder_background,R.color.background_gallery);
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question= getActivity().getString(R.string.remove_photo_message_alert);

                if(areYouSureDialog==null){
                    areYouSureDialog=new ParktoriaTwoOptions(getActivity().getString(R.string.remove_photo_message_alert));
                    areYouSureDialog.setTwoButtonListener(new ParktoriaTwoOptions.AcceptDeclineListener() {
                        @Override
                        public void onLeftButtonClicked() {
                            areYouSureDialog.dismiss();
                        }

                        @Override
                        public void onRightButtonClicked() {
                            returnValue();
                        }
                    });
                    areYouSureDialog.setLeftLabel(getActivity().getString(R.string.cancel));
                    areYouSureDialog.setRightLabel(getActivity().getString(R.string.warning_dialog_accept));
                    areYouSureDialog.setTitleResId(R.string.title_alert);
                }
                if(!areYouSureDialog.isAdded())
                    areYouSureDialog.show(getFragmentManager());
            }
        });
    }

    private void returnValue() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ExtraConstants.PHOTO_EXTRA,photo);
        bundle.putInt(ExtraConstants.PHOTO_POSITION_EXTRA,position);
        intent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }
}
