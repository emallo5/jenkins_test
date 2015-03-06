package com.aparcsystems.ui.view;

/**
 * Created by emi91_000 on 27/02/2015.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.aparcsystems.R;
import com.aparcsystems.ui.fragment.BaseFragment;


public class LoadingLayout extends FrameLayout {

    protected ProgressBar progressLoading;
    private boolean isLoading = true;

    public LoadingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingLayout(Context context) {
        super(context);
        init();
    }

    protected void init() {
        if(!isInEditMode()){
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.non_blocking_loading, this, true);
            progressLoading = (ProgressBar) this
                    .findViewById(R.id.loadingProgressBar);
        }
    }

    /**
     * @see android.view.View#onFinishInflate()
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViewState();
    }

    private void updateViewState() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == progressLoading) {
                child.setVisibility(isLoading ? VISIBLE : GONE);
            } else {
                child.setVisibility(isLoading ? INVISIBLE : VISIBLE);
            }
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        updateViewState();
    }

    public void startLoadingOnUIThread(BaseFragment fragment) {
        fragment.executeOnUiThread(new Runnable() {

            @Override
            public void run() {
                startLoading();
            }
        });
    }

    public void startLoading() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == progressLoading) {
                animateVisibility(child, VISIBLE);
            } else {
                child.setFocusable(false);
                animateVisibility(child, INVISIBLE);
            }
        }
    }

    public void stopLoadingOnUIThread(BaseFragment fragment) {
        fragment.executeOnUiThread(new Runnable() {

            @Override
            public void run() {
                stopLoading();
            }
        });
    }

    public void stopLoading() {
        if (!isLoading) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == progressLoading) {
                animateVisibility(child, GONE);
            } else {
                animateVisibility(child, VISIBLE);
            }
        }
        isLoading = false;
    }

    public boolean isLoadingVisible() {
        return progressLoading.getVisibility() == View.VISIBLE;
    }

    public void animateVisibility(final View view, final int visibility) {
        if ((visibility == VISIBLE) && (view.getVisibility() != VISIBLE)) {
            view.clearAnimation();
            view.setVisibility(VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getContext(),
                    android.R.anim.fade_in);
            animation.setFillBefore(true);
            view.startAnimation(animation);
        } else if ((visibility != VISIBLE) && (view.getVisibility() == VISIBLE)) {
            view.clearAnimation();
            view.setVisibility(visibility);
            Animation animation = AnimationUtils.loadAnimation(getContext(),
                    android.R.anim.fade_out);
            animation.setFillBefore(true);
            view.startAnimation(animation);
        }
    }
}