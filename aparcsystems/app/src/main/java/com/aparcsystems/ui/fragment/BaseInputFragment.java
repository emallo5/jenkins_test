package com.aparcsystems.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aparcsystems.model.Penalty;
import com.aparcsystems.task.RestAsyncTask;
import com.aparcsystems.task.ShowPenaltyTask;
import com.aparcsystems.ui.activity.ShowPenaltyActivity;

/**
 * Created by emi91_000 on 26/02/2015.
 */
public abstract class BaseInputFragment extends BaseFragment {
    private RestAsyncTask task;
    @Override
    public void onResume() {
        super.onResume();
        dismissFragmentLoadingOnUIThread();
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.unregister(this);
        bus.registerSticky(this);
    }

    public void onReceivePenaltyCode(String penaltyCode){
        startFragmentLoadingUIThread();
        if(task==null){
            task=new ShowPenaltyTask(getActivity(),penaltyCode);
            task.execute();
        }else{
            task.cancel(true);
            task=null;
            task=new ShowPenaltyTask(getActivity(),penaltyCode);
            task.execute();
        }

    }

    public void onEvent(ShowPenaltyTask.PenaltyEvent event){
        onGetPenalty(event.httpPenalty.getPenalty());
        bus.removeAllStickyEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(task!=null){
            task.cancel(true);
            task=null;
        }
    }

    public void onGetPenalty(Penalty penalty){
        ShowPenaltyActivity.startActivity(getActivity(), penalty);
    }
}
