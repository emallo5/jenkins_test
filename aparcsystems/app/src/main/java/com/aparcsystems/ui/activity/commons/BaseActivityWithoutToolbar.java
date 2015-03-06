package com.aparcsystems.ui.activity.commons;


import com.aparcsystems.R;

/**
 * Created by emi91_000 on 03/02/2015.
 */
public abstract class BaseActivityWithoutToolbar extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_without_toolbar;
    }
}
