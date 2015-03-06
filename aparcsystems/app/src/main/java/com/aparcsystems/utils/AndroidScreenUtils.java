package com.aparcsystems.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Emiliano on 17/02/2015.
 */
public class AndroidScreenUtils {

    public static Point getDisplayScreen(Activity context){
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
