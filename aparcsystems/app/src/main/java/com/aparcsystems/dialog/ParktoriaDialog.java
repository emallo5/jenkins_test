package com.aparcsystems.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by emi91_000 on 23/02/2015.
 */
public class ParktoriaDialog extends WarningDialog {
    public ParktoriaDialog() {
        super(0);
    }

    public ParktoriaDialog(String message) {
        super(message);
    }

    @Override
    protected void configureBuilder(AlertDialog.Builder builder) {
        super.configureBuilder(builder);
    }

}
