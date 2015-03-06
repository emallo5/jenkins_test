package com.aparcsystems.module;


import android.app.Application;

import com.aparcsystems.AparcsystemsApplication;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;

public class ApplicationModuleProvider extends AbstractModule {

    private final AparcsystemsApplication application;

    @Inject
    public ApplicationModuleProvider(final Application application) {
        super();
        this.application = (AparcsystemsApplication) application;
    }

    @Override
    protected void configure() {
        bind(AparcsystemsApplication.class).toInstance(application);
    }
}
