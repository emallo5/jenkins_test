package com.aparcsystems.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.aparcsystems.R;
import com.aparcsystems.dialog.OnAcceptListener;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.model.Penalty;
import com.aparcsystems.otto.Bus;
import com.aparcsystems.task.ShowPenaltyTask;
import com.aparcsystems.task.event.OnConnectionErrorEvent;
import com.aparcsystems.ui.activity.commons.BaseActivity;
import com.aparcsystems.ui.view.LoadingLayout;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import roboguice.fragment.RoboFragment;

/**
 * Created by emi91_000 on 23/12/2014.
 */
public abstract class BaseFragment<T> extends RoboFragment {

    private LoadingLayout loadingLayout;
    protected T callbacks;
    protected ParktoriaDialog parktoriaConnectionErrorDialog;
    protected ParktoriaDialog parktoriaApiErrorDialog;


    @Inject
    protected Bus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loadingLayout = (LoadingLayout) this.getView().findViewById(
                getLoadingContainer());
    }

    @Override
    public void onStart() {
        super.onStart();
        //IT is to save all cases fragment doesn't have suscriptions.
        try{
            bus.register(this);
        }catch(Exception e){

        }

    }

    @Override
    public void onStop() {
        //IT is to save all cases fragment doesn't have suscriptions.
        super.onStop();
        try{
            bus.unregister(this);
        }catch(Exception e){
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (T) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callback interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callbacks = null;
    }

    protected void startFragmentLoadingUIThread() {
        this.executeOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (BaseFragment.this.loadingLayout != null) {
                    BaseFragment.this.loadingLayout.startLoading();
                }
            }
        });
    }

    public void executeOnUiThread(Runnable runnable) {
        Activity activity = this.getActivity();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    protected void dismissFragmentLoadingOnUIThread() {
        if (this.loadingLayout != null) {
            this.loadingLayout.stopLoadingOnUIThread(BaseFragment.this);
        }

    }

    protected void dismissFragmentLoading() {
        if (this.loadingLayout != null) {
            this.loadingLayout.stopLoading();
        }
    }

    public void attendError(String errorMessage){

    }

    protected ActionBarActivity getActionBarActivity() {
        return (ActionBarActivity) getActivity();
    }
    protected  int getLoadingContainer(){
        return R.id.loading_container;
    }

    public void onEvent(OnConnectionErrorEvent event){
        if(parktoriaConnectionErrorDialog==null){
            parktoriaConnectionErrorDialog=new ParktoriaDialog(getActivity().getString(R.string.connection_error));
            parktoriaConnectionErrorDialog.setTitleResId(R.string.error);
            parktoriaConnectionErrorDialog.setCancelable(false);
            parktoriaConnectionErrorDialog.setOnAcceptListener(new OnAcceptListener() {
                @Override
                public void onAccept(String dialogTag) {
                    parktoriaConnectionErrorDialog.dismiss();
                }
            });
        }
        if(!parktoriaConnectionErrorDialog.isAdded())
            parktoriaConnectionErrorDialog.show(((BaseActivity) getActivity()).getSupportFragmentManager());
        dismissFragmentLoadingOnUIThread();
    }

    public void handleApiError(int messageResId){
        if(parktoriaApiErrorDialog==null){
            parktoriaApiErrorDialog=new ParktoriaDialog(getActivity().getString(messageResId));
            parktoriaApiErrorDialog.setTitleResId(R.string.error);
            parktoriaApiErrorDialog.setCancelable(false);
        }
        if(!parktoriaApiErrorDialog.isAdded())
            parktoriaApiErrorDialog.show(((BaseActivity) getActivity()).getSupportFragmentManager());
        dismissFragmentLoadingOnUIThread();
    }
}
