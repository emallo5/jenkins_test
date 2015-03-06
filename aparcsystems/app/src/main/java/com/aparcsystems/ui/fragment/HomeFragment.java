package com.aparcsystems.ui.fragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aparcsystems.R;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.mock.PenaltyMocks;
import com.aparcsystems.model.Penalty;
import com.aparcsystems.task.ShowPenaltyTask;
import com.aparcsystems.ui.activity.InputActivity;
import com.google.gson.Gson;

/**
 * Created by emi91_000 on 23/12/2014.
 */
public class HomeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.go_to_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputActivity.startActivity(getActivity(), ScanInputFragment.class.getName());
            }
        });
        getView().findViewById(R.id.go_to_manual_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputActivity.startActivity(getActivity(), ManualInputFragment.class.getName());
            }
        });
    }
}
