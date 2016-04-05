package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/29/16.
 * Proprietary (Hart)
 */
public class RelationshipFormatter extends FormattingKey
{
    @Override
    public String formatForDisplay(String raw)
    {
        if (raw == null || raw.length() == 0)
        {
            return "";
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

        return raw;
    }
}
