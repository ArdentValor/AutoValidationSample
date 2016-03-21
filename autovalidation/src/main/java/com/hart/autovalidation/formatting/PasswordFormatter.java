package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class PasswordFormatter extends FormattingKey
{
    @Override
    public String formatForDisplay(String raw)
    {
        return raw;
    }

    @Override
    public String formatForPost(String raw)
    {
        return raw;
    }
}
