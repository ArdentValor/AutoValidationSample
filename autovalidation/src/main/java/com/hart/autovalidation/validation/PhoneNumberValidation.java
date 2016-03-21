package com.hart.autovalidation.validation;

import android.util.Log;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class PhoneNumberValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        Log.i("PNV", "PNV CALLED!!!");
        ValidationResponse response = new ValidationResponse();
        response.isValid = isValidNumber(raw);
        response.responses.add((response.isValid) ? "valid number" : "please provide a valid 10 digit number");

        return response;
    }

    private boolean isValidNumber(String number)
    {
        // split incoming into an array
        String split[] = number.split("");
        // remove anything that is not a number
        String result = "";
        for (int i = 0; i < split.length; i++)
        {
            if (isValidInt(split[i]))
            {
                result += split[i];
            }
        }
        Log.i("PHONE FORMATER", "len " + result.length());
        // ensure that it is a 10 digit number
        return ((result.length() == 10  && !result.substring(0,1).equals("1")) || (result.length() == 11  && result.substring(0,1).equals("1")));
    }


    private boolean isValidInt(String n)
    {
        try
        {
            Long.parseLong(n);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}