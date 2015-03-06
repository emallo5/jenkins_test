package com.aparcsystems.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import com.aparcsystems.R;


@SuppressLint("ValidFragment")
public class AcceptDeclineDialog extends WarningDialog {
	private OnAcceptDeclineListener dialogListener;

	public AcceptDeclineDialog(String s,int title, OnAcceptDeclineListener onAcceptDeclineListener) {
		super(s);
        this.setTitleResId(title);
        dialogListener=onAcceptDeclineListener;
	}

	public AcceptDeclineDialog(int sureremovedate) {
		super(sureremovedate);
	}

	public void setDialogListener(OnAcceptDeclineListener dialogListener) {
		this.dialogListener = dialogListener;
	}

	@Override
	protected void configureBuilder(Builder builder) {
        super.configureBuilder(builder);
		builder.setPositiveButton(R.string.warning_dialog_accept,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						AcceptDeclineDialog.this.dismiss();
						if (dialogListener != null) {
							dialogListener.onAccept(AcceptDeclineDialog.this
									.getTag());
						}
					}
				});

		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						AcceptDeclineDialog.this.dismiss();
						if (dialogListener != null) {
							dialogListener.onCancel(AcceptDeclineDialog.this
									.getTag());
						}
					}
				});
	}
}
