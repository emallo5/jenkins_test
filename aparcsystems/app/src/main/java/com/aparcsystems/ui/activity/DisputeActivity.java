package com.aparcsystems.ui.activity;

import com.aparcsystems.ui.activity.commons.BaseActivityWithBack;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.DisputeFragment;
import com.aparcsystems.ui.fragment.PaymentFragment;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class DisputeActivity extends BaseActivityWithBack {
    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return DisputeFragment.class;
    }


}
