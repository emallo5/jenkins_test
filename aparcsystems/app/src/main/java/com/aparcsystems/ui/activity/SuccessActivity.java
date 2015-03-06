package com.aparcsystems.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.R;
import com.aparcsystems.ui.activity.commons.BaseActivityWithoutToolbar;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.SuccessFragment;
import com.aparcsystems.utils.ExtraConstants;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class SuccessActivity extends BaseActivityWithoutToolbar {

    public static void startActivity(Context context,int messageResId){
        Bundle bundle=new Bundle();
        bundle.putInt(ExtraConstants.MESSAGE_RES_ID_EXTRA,messageResId);
        Intent intent=new Intent(context,SuccessActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return SuccessFragment.class;
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(0, R.anim.get_out_back);
        startActivity(new Intent(this,HomeActivity.class));
    }
}
