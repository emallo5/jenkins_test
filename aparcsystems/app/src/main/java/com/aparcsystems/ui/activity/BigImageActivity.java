package com.aparcsystems.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.model.Photo;
import com.aparcsystems.ui.activity.commons.BaseActivityWithBack;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.BigImageFragment;
import com.aparcsystems.utils.ExtraConstants;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class BigImageActivity extends BaseActivityWithBack {

    public static void startActivityForResult(Activity context,Photo photo,int position,int requestCode){
        Intent intent=new Intent(context,BigImageActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(ExtraConstants.PHOTO_EXTRA,photo);
        bundle.putInt(ExtraConstants.PHOTO_POSITION_EXTRA,position);
        intent.putExtras(bundle);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return BigImageFragment.class;
    }
}
