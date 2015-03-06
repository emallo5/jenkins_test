package com.aparcsystems.ui.activity.commons;

import android.view.View;

import com.aparcsystems.ui.view.ObservableScrollView;

/**
 * Created by emi91_000 on 29/12/2014.
 */
public interface ObservableCallback {
        void onSetupFadingActionBar(ObservableScrollView observableScrollView, final View header);
    }