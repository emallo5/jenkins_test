package com.aparcsystems.task;

import android.content.Context;
import android.util.Log;

import com.aparcsystems.model.PenaltyError;
import com.aparcsystems.otto.Bus;
import com.aparcsystems.task.event.OnApiErrorEvent;
import com.aparcsystems.task.event.OnConnectionErrorEvent;
import com.aparcsystems.task.event.OnFinallyEvent;
import com.aparcsystems.task.event.OnPreExecuteEvent;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

import retrofit.RetrofitError;
import roboguice.util.RoboAsyncTask;

/**
 * AsyncTask to use with REST API Calls, it should handle HTTP Errors auto-magically.
 */
public abstract class RestAsyncTask<T> extends RoboAsyncTask<T> {

    @Inject
    protected Bus bus;

    @Inject
    protected RestAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected void onPreExecute() throws Exception {
        super.onPreExecute();
        bus.post(new OnPreExecuteEvent());
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        try {
            if(e instanceof RetrofitError) {
                RetrofitError retrofitError = (RetrofitError) e;
                if(retrofitError.getResponse() != null) {
                    if (retrofitError.getResponse().getStatus() > 500 ){
                        onConnectionError();
                        String msg = "Network error HTTP ("+retrofitError.getResponse().getStatus()+")";
                        if (retrofitError.getMessage()!=null && !retrofitError.getMessage().isEmpty()){
                            msg += ": "+retrofitError.getMessage();
                        }
                        super.onException(e);
                    }else if (retrofitError.getBody()==null){
                        onConnectionError();
                    }else if (retrofitError.getCause() instanceof ConnectException){
                        onConnectionError();
                    }else if (retrofitError.getCause() instanceof SocketTimeoutException){
                        onConnectionError();
                    }else{
						onApiError(((PenaltyError) retrofitError.getBodyAs(PenaltyError.class)).getMessage());
                    }
                }else if(retrofitError.getKind() == RetrofitError.Kind.NETWORK && !isCancelled()){
                    onConnectionError();
                    super.onException(e);
                }
            }else {
                super.onException(e);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void onInterrupted(Exception e) {
        Log.d("BACKGROUND_TASK", "Interrupting background task " + this);
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        bus.post(new OnFinallyEvent());
    }

    /**
     * Method to inherit if you need to add a more user friendly error. It should have an ErrorDTO or error message in String format.
     */
    protected void onApiError(String errorMessage) {
        bus.post(new OnApiErrorEvent(errorMessage));
    }
    protected void onConnectionError(){
        bus.post(new OnConnectionErrorEvent());
    }


}
