package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;
import com.hart.autovalidation.supportutils.GeneralUtils;

/**
 * Created by Alex on 3/28/16.
 * Proprietary (Hart)
 */
public class StateValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        boolean valid = false;

        for (int i = 0; i < GeneralUtils.STATES_ARRAY.length; i++)
        {
            if (raw.equals(GeneralUtils.STATES_ARRAY[i]))
            {
                valid = true;
                break;
            }
        }

        if (valid)
        {
            response.isValid = true;
            response.responses.add("Valid state");
        }
        else
        {
            response.isValid = false;
            response.responses.add("Invalid state");
        }

        return response;
    }
}
