package com.hart.autovalidation;

/**
 * Created by Alex on 3/17/16.
 * Proprietary (Hart)
 */
public class PasswordConfig
{
    public int minLength = 8;
    public int maxLength = 15;
    public boolean mustContainUpper = true;
    public boolean mustContainLower = true;
    public boolean mustContainNumber = true;
    public boolean mustContainSpecial = true;
}
