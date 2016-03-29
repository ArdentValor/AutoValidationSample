package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/28/16.
 * Proprietary (Hart)
 */
public class CityValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        // todo check vs list of cities? ..or enforce selection from set only
        if (raw != null && raw.length() > 0)
        {
            response.isValid = true;
            response.responses.add("valid city");
        }
        else
        {
            response.isValid = false;
            response.responses.add("invalid city");
        }
        return response;
    }
}
