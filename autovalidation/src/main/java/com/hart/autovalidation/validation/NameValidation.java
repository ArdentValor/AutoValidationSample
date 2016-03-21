package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class NameValidation extends ValidationKey
{
    private static final int NAME_CHARACTER_MIN = 1;

    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        response.isValid = raw.length() >= NAME_CHARACTER_MIN;
        response.responses.add((response.isValid) ? "name is valid" : "must be a valid name");
        return response;
    }
}
