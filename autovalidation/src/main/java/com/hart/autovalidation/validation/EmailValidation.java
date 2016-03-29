package com.hart.autovalidation.validation;

import android.text.TextUtils;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class EmailValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        response.isValid = isValidEmail(raw);

        if (response.isValid)
        {
            response.responses.add("Valid Email");
        }
        else
        {
            response.responses.add("Invalid Email");
        }
        return response;
    }

    public final static boolean isValidEmail(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
