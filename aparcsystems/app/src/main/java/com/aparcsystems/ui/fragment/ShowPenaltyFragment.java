package com.aparcsystems.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aparcsystems.R;
import com.aparcsystems.model.Penalty;
import com.aparcsystems.ui.activity.DisputeActivity;
import com.aparcsystems.ui.activity.PaymentActivity;
import com.aparcsystems.utils.DateFormatter;
import com.aparcsystems.utils.ExtraConstants;
import com.aparcsystems.utils.Lists;
import com.aparcsystems.utils.StringUtils;
import com.google.inject.Inject;

import roboguice.inject.InjectView;
import roboguice.util.Strings;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class ShowPenaltyFragment extends BaseFragment {
    private Penalty penalty;
    @InjectView(R.id.date_of_infraction_value)
    private TextView dateOfInfraction;
    @InjectView(R.id.time_of_infraction_value)
    private TextView timeOfInfraction;
    @InjectView(R.id.officer_value)
    private TextView officer;

    @InjectView(R.id.vehicle_license_number)
    private TextView vehicleLicenseNumber;
    @InjectView(R.id.vehicle_st_pr)
    private TextView vehicleStPr;
    @InjectView(R.id.vehicle_license_expiry)
    private TextView vehicleLicenseExpiry;
    @InjectView(R.id.vehicle_make)
    private TextView vehicleMake;
    @InjectView(R.id.vehicle_meter)
    private TextView vehicleMeter;
    @InjectView(R.id.infraction_location)
    private TextView infractionLocation;
    @InjectView(R.id.alleged_infraction_value)
    private TextView allegedInfraction;
    @InjectView(R.id.violation_description)
    private TextView violationDescription;
    @InjectView(R.id.comments1)
    private TextView comments1;
    @InjectView(R.id.comments2)
    private TextView comments2;
    @InjectView(R.id.comments3)
    private TextView comments3;
    @InjectView(R.id.paid_image)
    private ImageView paidImage;
    @InjectView(R.id.still_dispute)
    private TextView stillDispute;
    @InjectView(R.id.dispute)
    private TextView disputeButton;
    @InjectView(R.id.choose_payment)
    private Button choosePayment;
    @InjectView(R.id.photos_attached_container)
    private ViewGroup photosAttachedContainer;
    @InjectView(R.id.photos_separator)
    private View photosSeparator;

    @InjectView(R.id.photos_attached_text)
    private TextView photosAttachedText;
    @InjectView(R.id.amount_title)
    private TextView amountTitle;
    @InjectView(R.id.amount)
    private TextView amount;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_penalty_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        penalty= (Penalty) getArguments().getSerializable(ExtraConstants.PENALTY_EXTRA);

        disputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), DisputeActivity.class));
            }
        });
        choosePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), PaymentActivity.class));
            }
        });
        if(Lists.isNullOrEmpty(penalty.getPhotoList())){
            photosAttachedContainer.setVisibility(View.GONE);
            photosSeparator.setVisibility(View.GONE);
        }else{
            photosAttachedContainer.setVisibility(View.VISIBLE);
            photosAttachedText.setText(getActionBarActivity().getResources().getQuantityString(R.plurals.photo_attached, penalty.getPhotoList().size(), penalty.getPhotoList().size()));
        }
        showPenaltyDependsOnState();
        fillFields();
        showCommentsIfNeccesary();
    }

    private void fillFields() {
        fillField(dateOfInfraction, penalty.getDateOfInfraction());
        fillField(timeOfInfraction,penalty.getTimeOfInfraction());
        fillField(officer,penalty.getOfficerId());
        fillField(vehicleLicenseNumber,penalty.getPlate());
        fillField(vehicleStPr,penalty.getProvince());
        fillField(vehicleLicenseExpiry,DateFormatter.getMonthFromInteger(penalty.getLicenseExpMonth())+" "+penalty.getLicenseExpYear());
        fillField(vehicleMake,penalty.getVehicleMake());
        fillField(vehicleMeter,penalty.getMeterId());
        fillField(infractionLocation,penalty.getLocation());
        fillField(allegedInfraction,penalty.getOfferenceCode());
        fillField(violationDescription,penalty.getViolationDescription());
        fillField(comments1,penalty.getPublicComment1());
    }

    private void fillField(TextView textView,String value){
        if(Strings.isEmpty(value)){
            textView.setText(R.string.empty_value);
        }else{
            textView.setText(value);
        }
    }

    private void showCommentsIfNeccesary(){
            if(!StringUtils.isEmpty(penalty.getPublicComment2())){
                comments2.setVisibility(View.VISIBLE);
                comments2.setText(penalty.getPublicComment2());
                if(!StringUtils.isEmpty(penalty.getPublicComment3())){
                    comments3.setVisibility(View.VISIBLE);
                    comments3.setText(penalty.getPublicComment3());
                }else{
                    comments3.setVisibility(View.GONE);
                }
            }else{
                comments2.setVisibility(View.GONE);
                comments3.setVisibility(View.GONE);
            }
    }

    private void showPenaltyDependsOnState() {

        if(penalty.isPaid()){
            paidImage.setVisibility(View.VISIBLE);
            disputeButton.setVisibility(View.GONE);
            choosePayment.setVisibility(View.GONE);
            amountTitle.setText(R.string.amount_paid);
            amount.setText(getActivity().getString(R.string.amount_style,Float.toString(penalty.getPaidToDate())));
        }else{
            amount.setText(getActivity().getString(R.string.amount_style,Float.toString(penalty.getAmount())));
            if(penalty.isDisputed()){
                stillDispute.setVisibility(View.VISIBLE);
                disputeButton.setVisibility(View.GONE);
                LinearLayout.LayoutParams paymentParams= (LinearLayout.LayoutParams) choosePayment.getLayoutParams();
                paymentParams.weight=0;
                paymentParams.width= LinearLayout.LayoutParams.MATCH_PARENT;
                stillDispute.setLayoutParams(paymentParams);
            }
        }
    }


}
