package com.aparcsystems.ui.activity.commons;

import android.os.Bundle;

/**
 * Created by emi91_000 on 10/02/2015.
 */
public abstract class BaseActivityWithToolBar extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolBar();
    }
}
