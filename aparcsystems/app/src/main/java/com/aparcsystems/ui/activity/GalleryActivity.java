package com.aparcsystems.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.model.Photo;
import com.aparcsystems.ui.activity.commons.BaseActivityWithBack;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.GalleryFragment;
import com.aparcsystems.ui.fragment.ShowPenaltyFragment;
import com.aparcsystems.utils.ExtraConstants;

import java.util.List;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class GalleryActivity extends BaseActivityWithBack {
    public static void startActivityForResult(Activity context,List<Photo> photos,int requestCode){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ExtraConstants.PHOTOS_EXTRA, (java.io.Serializable) photos);
        Intent intent=new Intent(context,GalleryActivity.class);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return GalleryFragment.class;
    }

    @Override
    public void onBackPressed() {
        ((GalleryFragment)getMainFragment()).returnValue();
    }
}
