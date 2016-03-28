package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/28/16.
 * Proprietary (Hart)
 */
public class CountryValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        return response;
    }
}
