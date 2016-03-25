package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;
import com.hart.autovalidation.configuration.ConfigManager;
import com.hart.autovalidation.configuration.NameConfig;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class NameValidation extends ValidationKey
{
    private NameConfig nameConfig = null;


    @Override
    public ValidationResponse isValid(String raw)
    {
        if (nameConfig == null)
        {
            nameConfig = ConfigManager.getNameConfig();
        }

        ValidationResponse response = new ValidationResponse();
        response.isValid = raw.length() >= nameConfig.characterMinimum;
        response.responses.add((response.isValid) ? "name is valid" : "must be a valid name");
        return response;
    }
}
