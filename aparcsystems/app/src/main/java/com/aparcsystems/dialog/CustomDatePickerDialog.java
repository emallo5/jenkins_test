package com.aparcsystems.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aparcsystems.R;

import java.lang.reflect.Field;

/**
 * Created by emi91_000 on 21/02/2015.
 */
public class CustomDatePickerDialog extends DatePickerDialog {

    private int titleColorResId;
    private int dividerColorResId;

    public CustomDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public CustomDatePickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Resources res = getContext().getResources();
        changeTitleColor(res, res.getColor(titleColorResId));
        changeTitleDividerColor(res, res.getColor(dividerColorResId));
        hideDayAndChangePlaces();

        DatePicker dpView = this.getDatePicker();
        LinearLayout llFirst = (LinearLayout) dpView.getChildAt(0);
        LinearLayout llSecond = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < llSecond.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) llSecond.getChildAt(i); // Numberpickers in llSecond
            // reflection - picker.setDividerDrawable(divider); << didn't seem to work.
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setColor(getContext().getResources().getColor(dividerColorResId));
                        pf.set(picker, gd);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }



    }

    private void hideDayAndChangePlaces() {
        int day = getContext().getResources()
                .getIdentifier("android:id/day", null, null);
        int month = getContext().getResources()
                .getIdentifier("android:id/month", null, null);
        int year = getContext().getResources()
                .getIdentifier("android:id/year", null, null);
        View monthPicker = findViewById(month);
        View yearPicker = findViewById(year);
        ViewGroup parent=(ViewGroup)monthPicker.getParent();
        parent.removeView(monthPicker);
        parent.removeView(yearPicker);
        parent.addView(monthPicker);
        parent.addView(yearPicker);
        if(day != 0){
            View dayPicker = findViewById(day);
            if(dayPicker != null){
                dayPicker.setVisibility(View.GONE);
            }
        }
    }

    private void changeTitleDividerColor(Resources res, int color) {
        // Title divider
        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
        final View titleDivider = findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(color);
        }
    }

    private void changeTitleColor(Resources res, int color) {
        // Title
        final int titleId = res.getIdentifier("alertTitle", "id", "android");
        final View title = findViewById(titleId);
        if (title != null) {
            ((TextView) title).setTextColor(color);
        }
    }

    public int getTitleColorResId() {
        return titleColorResId;
    }

    public void setTitleColorResId(int titleColorResId) {
        this.titleColorResId = titleColorResId;
    }

    public int getDividerColorResId() {
        return dividerColorResId;
    }

    public void setDividerColorResId(int dividerColorResId) {
        this.dividerColorResId = dividerColorResId;
    }
}
