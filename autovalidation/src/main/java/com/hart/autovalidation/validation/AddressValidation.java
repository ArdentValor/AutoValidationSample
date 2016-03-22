package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class AddressValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        if (raw != null && raw.length() > 0)
        {
            response.isValid = true;
            response.responses.add("valid address");
        }
        else
        {
            response.isValid = false;
            response.responses.add("invalid address");
        }
        return response;
    }
}
