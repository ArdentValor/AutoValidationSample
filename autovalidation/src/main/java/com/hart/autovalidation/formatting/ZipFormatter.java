package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/28/16.
 * Proprietary (Hart)
 */
public class ZipFormatter extends FormattingKey
{
    @Override
    public String formatForDisplay(String raw)
    {
        if (raw == null || raw.length() == 0)
        {
            return "";
        }

        if (raw.length() >= 5)
        {
            raw = raw.substring(0, 5);
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
