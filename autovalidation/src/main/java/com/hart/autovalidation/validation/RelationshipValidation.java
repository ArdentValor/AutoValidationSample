package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;
import com.hart.autovalidation.supportutils.GeneralUtils;

/**
 * Created by Alex on 3/29/16.
 * Proprietary (Hart)
 */
public class RelationshipValidation extends ValidationKey
{
    @Override
    public ValidationResponse isValid(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        boolean valid = false;

        for (int i = 0; i < GeneralUtils.RELATIONS_ARRAY.length; i++)
        {
            if (raw.equals(GeneralUtils.RELATIONS_ARRAY[i]))
            {
                valid = true;
                break;
            }
        }

        if (valid)
        {
            response.isValid = true;
            response.responses.add("Valid relationship type");
        }
        else
        {
            response.isValid = false;
            response.responses.add("Invalid relationship type");
        }

        return response;
    }
}
