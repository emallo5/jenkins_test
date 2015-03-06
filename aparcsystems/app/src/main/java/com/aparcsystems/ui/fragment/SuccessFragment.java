package com.aparcsystems.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aparcsystems.R;
import com.aparcsystems.ui.activity.HomeActivity;
import com.aparcsystems.utils.ExtraConstants;

import roboguice.inject.InjectView;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class SuccessFragment extends BaseFragment {
    @InjectView(R.id.message)
    private TextView message;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.success_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        message.setText(getArguments().getInt(ExtraConstants.MESSAGE_RES_ID_EXTRA));
        getView().findViewById(R.id.continue_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeActivity.class));
            }
        });

    }
}
