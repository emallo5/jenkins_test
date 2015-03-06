package com.aparcsystems.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aparcsystems.R;
import com.aparcsystems.model.Photo;

/**
 * Created by Emiliano on 16/02/2015.
 */
public abstract class PickImageFragment extends BaseFragment {
    protected static final int CAMERA_REQUEST_CODE = 0;
    protected static final int GALLERY_PICTURE_REQUEST_CODE = 1;
    protected static final String DIALOG_FRAGMENT = "dialogFragment";
    private Intent pictureActionIntent = null;
    private String selectedImagePath;
    private ParktoriaTwoOptions dialog;
    private ParktoriaTwoOptions.AcceptDeclineListener dialogListener;

    public PickImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogListener=new ParktoriaTwoOptions.AcceptDeclineListener() {
            @Override
            public void onLeftButtonClicked() {
                pictureActionIntent = new Intent(
                        Intent.ACTION_GET_CONTENT, null);
                pictureActionIntent.setType("image/*");
                pictureActionIntent.putExtra("return-data", true);
                getActivity().startActivityForResult(pictureActionIntent,
                        GALLERY_PICTURE_REQUEST_CODE);
            }

            @Override
            public void onRightButtonClicked() {
                pictureActionIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(pictureActionIntent,
                        CAMERA_REQUEST_CODE);

            }
        };
//        if(savedInstanceState!=null){
//            if(savedInstanceState.getSerializable(DIALOG_FRAGMENT)!=null){
//                dialog= (ParktoriaTwoOptions) savedInstanceState.getSerializable(DIALOG_FRAGMENT);
//                dialog.setTwoButtonListener(dialogListener);
//            }
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pick_image_fragment,container,false);
    }

    protected void startDialog() {
        if(dialog==null){
            dialog=new ParktoriaTwoOptions("How do you want to set your picture?");
            dialog.setTwoButtonListener(dialogListener);
            dialog.setLeftLabel(getActivity().getString(R.string.gallery_option));
            dialog.setRightLabel(getActivity().getString(R.string.camera_option));
            dialog.setTitleResId(R.string.gallery_or_camera_title);
        }
        if(!dialog.isAdded())
            dialog.show(getFragmentManager());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_PICTURE_REQUEST_CODE) {
                receiveGalleryImage(data);
            } else if (requestCode == CAMERA_REQUEST_CODE) {
                receiveImageFromCamera(data);
            }
        }
    }

    private void receiveImageFromCamera(Intent data) {
        if (data.hasExtra("data")) {
            // retrieve the bitmap from the intent
            Cursor cursor = getActionBarActivity().getContentResolver()
                    .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{
                                    MediaStore.Images.Media.DATA,
                                    MediaStore.Images.Media.DATE_ADDED,
                                    MediaStore.Images.ImageColumns.ORIENTATION},
                            MediaStore.Images.Media.DATE_ADDED, null, "date_added ASC");
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Uri uri = Uri.parse(cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA)));
                    selectedImagePath = uri.toString();
                } while (cursor.moveToNext());
                cursor.close();
                onTokenPhoto(new Photo(selectedImagePath,data.getData()));
            }else{
                getActivity().onBackPressed();
            }
        }else{
            getActivity().onBackPressed();
        }
    }

    private void receiveGalleryImage(Intent data) {
        if (data != null) {
            // try to retrieve the image using the data from the intent
            Cursor cursor = getActivity().getContentResolver().query(data.getData(),
                    null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                selectedImagePath = cursor.getString(idx);
                onTokenPhoto(new Photo(selectedImagePath,data.getData()));
            }
        } else {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        if(dialog!=null)
//            outState.putSerializable(DIALOG_FRAGMENT,dialog);
    }

    protected abstract void onTokenPhoto(Photo photo);
}
