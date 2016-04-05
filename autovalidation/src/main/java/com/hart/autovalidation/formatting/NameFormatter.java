package com.hart.autovalidation.formatting;

import com.hart.autovalidation.configuration.ConfigManager;
import com.hart.autovalidation.configuration.NameConfig;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class NameFormatter extends FormattingKey
{
    private NameConfig nameConfig = null;

    @Override
    public String formatForDisplay(String raw)
    {
        if (nameConfig == null)
        {
            nameConfig = ConfigManager.getNameConfig();
        }

        if (raw == null || raw.length() == 0)
        {
            return "";
        }

        switch (nameConfig.formatRule)
        {
            case NameConfig.FORMAT_RULE_ALL_CAPS:
                raw = raw.toUpperCase();
                break;
            case NameConfig.FORMAT_RULE_LEADING_CAP:
                raw = raw.substring(0, 1).toUpperCase() + raw.substring(1).toLowerCase();
                break;
            case NameConfig.FORMAT_RULE_UNALTERED:
                // do nothing
                break;
        }
        return raw;
    }

    @Override
    public String formatForPost(String raw)
    {
        if (raw == null || raw.length() == 0)
        {
            return "";
        }

        return formatForDisplay(raw);
    }
}
