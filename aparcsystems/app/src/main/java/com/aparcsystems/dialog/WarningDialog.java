package com.aparcsystems.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aparcsystems.R;


@SuppressLint("ValidFragment")
public class WarningDialog extends BaseDialogFragment {
    public static final String TITLE_RES_ID_EXTRA ="titleId";
    public static final String MESSAGE_EXTRA="message";
    public static final String MESSAGE_RES_ID_EXTRA="messageResId";

    private int titleResId;

    private int titleColorResId= R.color.standard_green;

    private int dividerColorResId=R.color.standard_green;

	private OnAcceptListener listener;

	public WarningDialog(Integer messageResId) {
		super(messageResId);
	}

	public WarningDialog(String message) {
		super(message);
	}

	protected void onPositiveButtonClicked() {
		if (listener != null) {
			listener.onAccept(this.getTag());
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            string=savedInstanceState.getString(MESSAGE_EXTRA);
            titleResId=savedInstanceState.getInt(TITLE_RES_ID_EXTRA);
            messageResId=savedInstanceState.getInt(MESSAGE_RES_ID_EXTRA);
        }

        final Resources res = getActivity().getResources();
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        changeTitleDividerColor(res, res.getColor(dividerColorResId),dialog);
        return super.onCreateDialog(savedInstanceState);
    }

    private void changeTitleDividerColor(Resources res, int color,Dialog dialog) {
        // Title divider
        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
        final View titleDivider = dialog.getWindow().getDecorView().findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(color);
        }
    }


    public void setOnAcceptListener(OnAcceptListener listener) {
		this.listener = listener;
	}

	@Override
	protected void configureBuilder(Builder builder) {
		builder.setPositiveButton(R.string.warning_dialog_accept,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						WarningDialog.this.dismiss();
						WarningDialog.this.onPositiveButtonClicked();
					}
				});
        TextView titleView= (TextView) View.inflate(getActivity(), R.layout.parktoria_dialog_title, null);
        titleView.setText(titleResId);
        builder.setCustomTitle(titleView);
	}

    public int getDividerColorResId() {
        return dividerColorResId;
    }

    public void setDividerColorResId(int dividerColorResId) {
        this.dividerColorResId = dividerColorResId;
    }

    public int getTitleColorResId() {
        return titleColorResId;
    }

    public void setTitleColorResId(int titleColorResId) {
        this.titleColorResId = titleColorResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TITLE_RES_ID_EXTRA,titleResId);
        outState.putString(MESSAGE_EXTRA, string);
        outState.putInt(MESSAGE_RES_ID_EXTRA,messageResId);
    }
}
