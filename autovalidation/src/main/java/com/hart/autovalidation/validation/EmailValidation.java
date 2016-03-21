package com.hart.autovalidation.validation;

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
        // todo : write email validation
        ValidationResponse response = new ValidationResponse();
        response.isValid = true;
        response.responses.add("valid email");
        return null;
    }
}
