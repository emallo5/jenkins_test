package com.aparcsystems.utils;

import java.util.regex.Pattern;

/**
 * Created by emi91_000 on 17/02/2015.
 */
public class ValidationUtils {

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
