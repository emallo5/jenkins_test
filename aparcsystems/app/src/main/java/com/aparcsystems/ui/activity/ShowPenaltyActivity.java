package com.aparcsystems.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aparcsystems.model.Penalty;
import com.aparcsystems.ui.activity.commons.BaseActivityWithBack;
import com.aparcsystems.ui.activity.commons.BaseActivityWithToolBar;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.ShowPenaltyFragment;
import com.aparcsystems.utils.ExtraConstants;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class ShowPenaltyActivity extends BaseActivityWithBack {
    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return ShowPenaltyFragment.class;
    }

    public static void startActivity(Context context,Penalty penalty){
        Intent intent=new Intent(context,ShowPenaltyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(ExtraConstants.PENALTY_EXTRA,penalty);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
