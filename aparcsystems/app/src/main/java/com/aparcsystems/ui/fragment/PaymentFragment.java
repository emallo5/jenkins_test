package com.aparcsystems.ui.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aparcsystems.AparcsystemsApplication;
import com.aparcsystems.R;
import com.aparcsystems.dialog.CustomDatePickerDialog;
import com.aparcsystems.dialog.ParktoriaDialog;
import com.aparcsystems.ui.activity.SuccessActivity;
import com.aparcsystems.ui.activity.commons.BaseActivity;
import com.aparcsystems.utils.StringUtils;
import com.aparcsystems.utils.ValidationUtils;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

import java.util.Calendar;

import roboguice.inject.InjectView;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class PaymentFragment extends BaseFragment {
    @InjectView(R.id.email)
    private EditText email;
    @InjectView(R.id.credit_card)
    private EditText creditcard;
    @InjectView(R.id.date)
    private EditText date;
    @InjectView(R.id.cvc)
    private EditText cvc;
    private CustomDatePickerDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payment_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!StringUtils.isEmpty(email.getText().toString()) && !ValidationUtils.isValidEmail(email.getText().toString())) {
                    email.setError(getActivity().getString(R.string.invalid_email_error));
                } else {
                    removeTextError(email);
                }
            }
        });
        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePicker();
                }
                return true;
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showDatePicker();
                }
            }
        });
        cvc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeTextError(cvc);
            }
        });
        creditcard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeTextError(creditcard);
            }
        });

        cvc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    pay();
                }
                return false;
            }
        });
        getView().findViewById(R.id.pay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay();
            }
        });
    }

    public void showDatePicker(){
        if (dialog == null) {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH) + 1;
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            dialog = new CustomDatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String stringMonth = String.format("%02d", monthOfYear + 1);
                    date.setText(stringMonth + "/" + String.valueOf(year));
                    dialog.setTitle(R.string.date_picker_title);
                    removeTextError(date);
                }
            }, mYear, mMonth, mDay);
            dialog.setTitle(R.string.date_picker_title);
            dialog.setDividerColorResId(R.color.standard_green);
            dialog.setTitleColorResId(R.color.standard_green);
        }
        dialog.show();
    }

    public void pay(){
        if(!validateInput()){
            Card card = new Card("4242424242424242", 12, 2015, "123");

            if(!card.validateCard()){
                //TODO show errors)
            }

            Stripe stripe = null;
            try {
                stripe = new Stripe(AparcsystemsApplication.STRIPE_API_KEY);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
            if(stripe!=null){
                stripe.createToken(
                        card,
                        new TokenCallback() {
                            public void onSuccess(Token token) {
                                Toast.makeText(getActivity(),
                                        "token: " + token,
                                        Toast.LENGTH_LONG
                                ).show();
                                SuccessActivity.startActivity(getActivity(), R.string.success_pay);
                            }
                            public void onError(Exception error) {
                                showErrorDialog();
                            }
                        }
                );
            }
        }
    }

    private void showErrorDialog(){
        ParktoriaDialog parktoriaDialog=new ParktoriaDialog(getActivity().getString(R.string.payment_error));
        parktoriaDialog.setTitleResId(R.string.error);
        parktoriaDialog.show(((BaseActivity) getActivity()).getSupportFragmentManager());
    }

    private void removeTextError(EditText text) {
        if (!StringUtils.isEmpty(text.getText().toString())) {
            text.setError(null);
        }
    }

    private Boolean validateInput(){
        Boolean haveError=false;
        if (StringUtils.isEmpty(cvc.getText().toString())) {
            cvc.setError(getActivity().getString(R.string.cvc_error));
            cvc.requestFocus();
            haveError=true;
        }

        if (StringUtils.isEmpty(creditcard.getText().toString())) {
            creditcard.setError(getActivity().getString(R.string.card_number_error));
            creditcard.requestFocus();
            haveError=true;
        }
        if (StringUtils.isEmpty(date.getText().toString())) {
            date.setError(getActivity().getString(R.string.date_error));
            date.requestFocus();
            haveError=true;
        }
        if (StringUtils.isEmpty(email.getText().toString())) {
            email.setError(getActivity().getString(R.string.email_error));
            email.requestFocus();
            haveError=true;
        }else if (!StringUtils.isEmpty(email.getText().toString()) && !ValidationUtils.isValidEmail(email.getText().toString())) {
                email.setError(getActivity().getString(R.string.invalid_email_error));
                email.requestFocus();
                haveError = true;

        }
        return haveError;
    }

}
