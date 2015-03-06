package com.aparcsystems.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.dialog.ParktoriaDialog;

import java.io.Serializable;

/**
 * Created by emi91_000 on 05/03/2015.
 */
public class ParktoriaTwoOptions extends ParktoriaDialog {
    protected final static String LEFT_LABEL="leftLabel";
    protected final static String RIGHT_LABEL="rightLabel";
    protected final static String LISTENER="listener";
    private AcceptDeclineListener twoButtonListener;
    private String leftLabel;
    private String rightLabel;

    public ParktoriaTwoOptions() {
    }

    public ParktoriaTwoOptions(String message) {
        super(message);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setRetainInstance(true);
        if(savedInstanceState!=null){
            leftLabel=savedInstanceState.getString(LEFT_LABEL);
            rightLabel=savedInstanceState.getString(RIGHT_LABEL);
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    protected void configureBuilder(AlertDialog.Builder builder) {
        super.configureBuilder(builder);

        builder.setPositiveButton(leftLabel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if(twoButtonListener !=null)
                            twoButtonListener.onLeftButtonClicked();
                    }
                });

        builder.setNegativeButton(rightLabel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if(twoButtonListener !=null)
                            twoButtonListener.onRightButtonClicked();
                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LEFT_LABEL,leftLabel);
        outState.putString(RIGHT_LABEL,rightLabel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface AcceptDeclineListener{
        public void onLeftButtonClicked();
        public void onRightButtonClicked();
    }

    public AcceptDeclineListener getTwoButtonListener() {
        return twoButtonListener;
    }

    public void setTwoButtonListener(AcceptDeclineListener twoButtonListener) {
        this.twoButtonListener = twoButtonListener;
    }

    public String getLeftLabel() {
        return leftLabel;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel = leftLabel;
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
    }
}
