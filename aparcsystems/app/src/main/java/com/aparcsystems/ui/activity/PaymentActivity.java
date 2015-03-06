package com.aparcsystems.ui.activity;

import com.aparcsystems.ui.activity.commons.BaseActivityWithBack;
import com.aparcsystems.ui.activity.commons.BaseActivityWithToolBar;
import com.aparcsystems.ui.fragment.BaseFragment;
import com.aparcsystems.ui.fragment.PaymentFragment;
import com.aparcsystems.ui.fragment.ShowPenaltyFragment;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class PaymentActivity extends BaseActivityWithBack {
    @Override
    public Class<? extends BaseFragment> getFragmentClass() {
        return PaymentFragment.class;
    }
}
