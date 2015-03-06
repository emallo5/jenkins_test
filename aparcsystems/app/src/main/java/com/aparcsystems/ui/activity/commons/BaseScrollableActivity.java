package com.aparcsystems.ui.activity.commons;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.aparcsystems.R;
import com.aparcsystems.ui.view.ObservableScrollView;

/**
 * Created by emi91_000 on 02/01/2015.
 */
public abstract class BaseScrollableActivity extends BaseActivity implements ObservableCallback {

    private Drawable mActionBarBackgroundDrawable;
    protected final int INITIAL_ALPHA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolBarWithBack();
        setTitle("");
        mActionBarBackgroundDrawable = getResources().getDrawable(R.color.abc_search_url_text);
        mActionBarBackgroundDrawable.setAlpha(INITIAL_ALPHA);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            toolbar.setBackgroundDrawable(mActionBarBackgroundDrawable);
        } else {
            toolbar.setBackground(mActionBarBackgroundDrawable);
        }
    }

    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            toolbar.setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
            who.setAlpha(255);
        }
    };

    @Override
    public void onSetupFadingActionBar(ObservableScrollView observableScrollView, final View header) {
        observableScrollView.setObservableScrollViewListener(new ObservableScrollView.ObservableScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                final int headerHeight = (header.getHeight() - toolbar.getHeight());
                final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) Math.max((ratio * 255),INITIAL_ALPHA);
                mActionBarBackgroundDrawable.setAlpha(newAlpha);
//
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addStatusBarAlpha(newAlpha);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActionBarBackgroundDrawable.unscheduleSelf(null);
        mActionBarBackgroundDrawable = null;
    }

    protected void addStatusBarAlpha(int newAlpha) {
        //By default do nothing...
    }
}
