package com.aparcsystems.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class IntentBuilder {


    public static Intent getBrowserIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    public static Intent getTapatalkIntent(ActionBarActivity activity) {
        Intent intent = null;
        final String tapatalkPackage = "com.quoord.tapatalkpro.activity";

        try {
            PackageManager pm = activity.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(tapatalkPackage, PackageManager.GET_ACTIVITIES);

            if(packageInfo != null) {
                intent = pm.getLaunchIntentForPackage(tapatalkPackage);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
            }

        }catch (ActivityNotFoundException e) {
            Log.e(IntentBuilder.class.toString(), "Activity Not Found", e);
        }catch (PackageManager.NameNotFoundException e) {
            Log.e(IntentBuilder.class.toString(), "Name Not Found", e);
        }

        return intent;
    }

}
