package com.aparcsystems.otto;

import android.os.Handler;
import android.os.Looper;

import com.google.inject.Singleton;

import de.greenrobot.event.EventBus;

/**
 * Injected Class to use Otto with a Singleton instance and to post always on the main thread.
 * <br>
 * https://github.com/square/otto/issues/38
 */
@Singleton
public class Bus extends EventBus {

    private final Handler mainThread = new Handler(Looper.getMainLooper());


    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    Bus.super.post(event);
                }
            });
        }
    }


}
