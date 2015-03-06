package com.aparcsystems;

import android.app.Application;

import com.aparcsystems.module.ApplicationModuleProvider;
import com.google.inject.util.Modules;

import roboguice.RoboGuice;


public class AparcsystemsApplication extends Application {

    public static final String STRIPE_API_KEY="pk_test_czwy4dEs1OzBhZHaSIxYgbS3";

    @Override
    public void onCreate() {
        super.onCreate();


        RoboGuice.setUseAnnotationDatabases(true);
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                                                     Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new ApplicationModuleProvider(this)));

    }


}
