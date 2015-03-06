package com.aparcsystems.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aparcsystems.R;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.model.Photo;
import com.aparcsystems.ui.activity.GalleryActivity;
import com.aparcsystems.ui.activity.SuccessActivity;
import com.aparcsystems.ui.view.RoundedImageView;
import com.aparcsystems.utils.BitmapUtils;
import com.aparcsystems.utils.ExtraConstants;
import com.aparcsystems.utils.StringUtils;
import com.aparcsystems.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class DisputeFragment extends PickImageFragment {
    public static final int SHOW_IMAGES_REQUEST=2;
    private List<Photo> photos=new ArrayList<Photo>();
    @InjectView(R.id.photos_attached_container)
    private ViewGroup photosAttachedContainer;
    @InjectView(R.id.photos_attached_text)
    private TextView photosAttachedText;
    @InjectView(R.id.photos_attached_logo)
    private RoundedImageView photosAttachedLogo;
    @InjectView(R.id.dispute_button)
    private Button disputeButton;
    @InjectView(R.id.layout)
    private RelativeLayout relativeLayout;
    @InjectView(R.id.take_photo)
    private Button takePhotoButton;

    @InjectView(R.id.email)
    private EditText email;
    @InjectView(R.id.last_name)
    private EditText lastName;
    @InjectView(R.id.name)
    private EditText name;
    @InjectView(R.id.mobile)
    private EditText mobile;
    @InjectView(R.id.comments)
    private EditText comments;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dispute_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!StringUtils.isEmpty(email.getText().toString()) && !ValidationUtils.isValidEmail(email.getText().toString())) {
                    email.setError(getActivity().getString(R.string.invalid_email_error));
                } else {
                    removeTextError(email);
                }
            }
        });
        photosAttachedContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryActivity.startActivityForResult(getActivity(), photos, SHOW_IMAGES_REQUEST);
            }
        });
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeTextError(lastName);
            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeTextError(name);
            }
        });
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeTextError(mobile);
            }
        });
        comments.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    dispute();
                }
                return false;
            }
        });
        disputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispute();
            }
        });
        if(savedInstanceState!=null){
            photos= (List<Photo>) savedInstanceState.getSerializable(ExtraConstants.PHOTOS_EXTRA);
            refreshList();
        }
    }

    private void dispute(){
        if(!validateInput()){
            if(StringUtils.isEmpty(comments.getText().toString())){
                ParktoriaDialog dialog=new ParktoriaDialog(getActivity().getString(R.string.photo_or_comments_required));
                dialog.setTitleResId(R.string.title_alert);
                dialog.show(getFragmentManager());
            }else{
                SuccessActivity.startActivity(getActivity(), R.string.success_dispute);
            }
        }
    }

    private void removeTextError(EditText text) {
        if (!StringUtils.isEmpty(text.getText().toString())) {
            text.setError(null);
        }
    }

    private Boolean validateInput(){
        Boolean haveError=false;
        if (StringUtils.isEmpty(mobile.getText().toString())) {
            mobile.setError(getActivity().getString(R.string.mobile_error));
            haveError=true;
        }

        if (StringUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError(getActivity().getString(R.string.lastname_error));
            haveError=true;
        }
        if (StringUtils.isEmpty(name.getText().toString())) {
            name.setError(getActivity().getString(R.string.name_error));
            haveError=true;
        }
        if (StringUtils.isEmpty(email.getText().toString())) {
            email.setError(getActivity().getString(R.string.email_error));
            email.requestFocus();
            haveError=true;
        }else if (!StringUtils.isEmpty(email.getText().toString()) && !ValidationUtils.isValidEmail(email.getText().toString())) {
            email.setError(getActivity().getString(R.string.invalid_email_error));
            email.requestFocus();
            haveError = true;
        }
        return haveError;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
          if(requestCode==SHOW_IMAGES_REQUEST){
                photos= (List<Photo>) data.getSerializableExtra(ExtraConstants.PHOTOS_EXTRA);
                refreshList();
            }
        }
    }

    @Override
    protected void onTokenPhoto(Photo photo) {
        addPhotoToList(photo);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void addPhotoToList(Photo photo){
        photos.add(photo);
        refreshList();
    }

    public void removePhotoFromList(Photo photo){
        photos.remove(photo);
        refreshList();
    }

    public void refreshList(){
        if(photos!=null && !photos.isEmpty()){
            photosAttachedContainer.setVisibility(View.VISIBLE);
            BitmapUtils.showImage(getActivity(),photosAttachedLogo.getLayoutParams().width,photosAttachedContainer.getLayoutParams().height,photos.get(0).getPath(),photosAttachedLogo,photos.get(0).getUri());
            ((RelativeLayout.LayoutParams)disputeButton.getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.photos_attached_container);
            photosAttachedText.setText(getActionBarActivity().getResources().getQuantityString(R.plurals.photo_attached, photos.size(), photos.size()));
        }else{
            RelativeLayout.LayoutParams params=((RelativeLayout.LayoutParams)disputeButton.getLayoutParams());
            params.addRule(RelativeLayout.BELOW, R.id.comments);
            disputeButton.setLayoutParams(params);
            photosAttachedContainer.setVisibility(View.INVISIBLE);
        }

        if(photos.size()<6){
            takePhotoButton.setVisibility(View.VISIBLE);
        }else{
            takePhotoButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ExtraConstants.PHOTOS_EXTRA, (java.io.Serializable) photos);
    }
}
