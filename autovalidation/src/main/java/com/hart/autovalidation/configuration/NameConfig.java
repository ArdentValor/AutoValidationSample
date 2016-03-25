package com.hart.autovalidation.configuration;

/**
 * Created by Alex on 3/24/16.
 * Proprietary (Hart)
 */
public class NameConfig
{
    public static final int FORMAT_RULE_ALL_CAPS = 0;
    public static final int FORMAT_RULE_LEADING_CAP = 1;
    public static final int FORMAT_RULE_UNALTERED = 2;
    public int formatRule = 1;

    public int characterMinimum = 1;
}
