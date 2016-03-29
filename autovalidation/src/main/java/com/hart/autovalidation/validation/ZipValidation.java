package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/28/16.
 * Proprietary (Hart)
 */
public class ZipValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();

        if (raw != null && raw.length() == 5)
        {
            response.isValid = true;
            response.responses.add("valid zip");
        }
        else
        {
            response.isValid = false;
            response.responses.add("invalid zip");
        }
        return response;
    }
}
