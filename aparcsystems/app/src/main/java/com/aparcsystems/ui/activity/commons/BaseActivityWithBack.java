package com.aparcsystems.ui.activity.commons;

import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by emi91_000 on 03/02/2015.
 */
public abstract class BaseActivityWithBack extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolBarWithBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
