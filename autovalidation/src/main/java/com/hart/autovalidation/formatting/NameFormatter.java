package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class NameFormatter extends FormattingKey
{
    private static final int FORMAT_RULE_ALL_CAPS = 0;
    private static final int FORMAT_RULE_LEADING_CAP = 1;
    private static final int FORMAT_RULE_UNALTERED = 2;
    private int formatRule = 1;

    @Override
    public String formatForDisplay(String raw)
    {
        switch (formatRule)
        {
            case FORMAT_RULE_ALL_CAPS:
                raw = raw.toUpperCase();
                break;
            case FORMAT_RULE_LEADING_CAP:
                raw = raw.substring(0, 1).toUpperCase() + raw.substring(1).toLowerCase();
                break;
            case FORMAT_RULE_UNALTERED:
                // do nothing
                break;
        }
        return raw;
    }

    @Override
    public String formatForPost(String raw)
    {
        return formatForDisplay(raw);
    }
}
