package com.aparcsystems.utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ViewAnimationUtils {

    public static final int FADE_DURATION = 600;

    public static ObjectAnimator buildFadeIn(View view, int duration) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(duration);
        return anim;
    }


    public static void fadeIn(View view, int duration) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(duration);
        anim.start();
        view.setVisibility(View.VISIBLE);
    }

    public static void fadeIn(View view) {
        fadeIn(view, FADE_DURATION);
    }


}
