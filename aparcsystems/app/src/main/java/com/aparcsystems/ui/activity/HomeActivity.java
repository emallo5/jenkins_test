package com.aparcsystems.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.R;
import com.aparcsystems.ui.activity.commons.BaseActivity;
import com.aparcsystems.ui.activity.commons.BaseActivityWithoutToolbar;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.HomeFragment;


public class HomeActivity extends BaseActivityWithoutToolbar {


    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return HomeFragment.class;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.get_in, R.anim.get_out);
    }
}
