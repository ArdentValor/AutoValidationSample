package com.hart.autovalidation.formatting;

import com.hart.autovalidation.configuration.ConfigManager;
import com.hart.autovalidation.configuration.SSNConfig;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class SSNFormatter extends FormattingKey
{
    private SSNConfig ssnConfig = null;

    @Override
    public String formatForDisplay(String raw)
    {
        if (ssnConfig == null)
        {
            ssnConfig = ConfigManager.getSSNConfig();
        }


        raw = raw.replace("-", "");

        if (ssnConfig.formatConfig == ssnConfig.LAST_FOUR)
        {
            if (raw.length() >= 4)
            {
                raw = raw.substring(0, 4);
            }
        }
        else if (ssnConfig.formatConfig == ssnConfig.FULL_NUMBER)
        {
            if (raw.length() < 3)
            {
                // do nothing
            }
            else if (raw.length() == 3)
            {
                raw += "-";
            }
            else if (raw.length() == 4)
            {
                raw = raw.substring(0, 3) + "-" + raw.substring(3);
            }
            else if (raw.length() == 5)
            {
                raw = raw.substring(0, 3) + "-" + raw.substring(3) + "-";
            }
            else if (raw.length() > 5)
            {
                raw = raw.substring(0, 3) + "-" + raw.substring(3, 5) + "-" + raw.substring(5);
            }

            if (raw.length() > 11)
            {
                raw = raw.substring(0, 11);
            }
        }

        return raw;
    }

    @Override
    public String formatForPost(String raw)
    {
        return raw;
    }
}
