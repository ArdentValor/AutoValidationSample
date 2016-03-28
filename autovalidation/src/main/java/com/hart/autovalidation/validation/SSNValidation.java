package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;
import com.hart.autovalidation.configuration.ConfigManager;
import com.hart.autovalidation.configuration.SSNConfig;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class SSNValidation extends ValidationKey
{
    private SSNConfig ssnConfig;

    @Override
    public ValidationResponse isValid(String raw)
    {
        if (ssnConfig == null)
        {
            ssnConfig = ConfigManager.getSSNConfig();
        }

        ValidationResponse response = new ValidationResponse();

        if (ssnConfig.formatConfig == SSNConfig.LAST_FOUR)
        {
            if (raw.length() == 4)
            {
                response.isValid = true;
                response.responses.add("SSN is valid");
            }
            else
            {
                response.isValid = false;
                response.responses.add("Must be a valid length");
            }
        }
        else
        {
            if (raw.length() == 11)
            {
                response.isValid = true;
                response.responses.add("SSN is valid");
            }
            else
            {
                response.isValid = false;
                response.responses.add("Must be a valid length");
            }
        }

        return response;
    }
}
