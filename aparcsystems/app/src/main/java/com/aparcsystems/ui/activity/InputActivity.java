package com.aparcsystems.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.ui.activity.commons.BaseActivityWithoutToolbar;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.ManualInputFragment;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class InputActivity extends BaseActivityWithoutToolbar {
    private static final String CLASS_NAME="className";
    public static void startActivity(Context context,String className) {
        Intent intent= new Intent(context,InputActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(CLASS_NAME, className);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        try {
            return (Class<? extends BaseFragment>) Class.forName((String)getIntent().getExtras().get(CLASS_NAME));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
