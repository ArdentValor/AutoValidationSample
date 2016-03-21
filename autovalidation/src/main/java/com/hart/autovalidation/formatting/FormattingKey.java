package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public abstract class FormattingKey
{
    public abstract String formatForDisplay(String raw);

    public abstract String formatForPost(String raw);
}
