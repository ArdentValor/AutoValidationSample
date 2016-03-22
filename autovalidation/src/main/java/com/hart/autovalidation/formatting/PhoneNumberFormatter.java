package com.hart.autovalidation.formatting;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class PhoneNumberFormatter extends FormattingKey
{
    @Override
    public String formatForDisplay(String number)
    {
        if (number == null || number.length() == 0)
        {
            return "";
        }

        boolean leadingOne = number.substring(0, 1).equals("1");
        number = (leadingOne) ? number.substring(1) : number;

        String raw[] = formatForPost(number).split("");
        String result = "(";

        for (int i = 0; i < raw.length && i < 4; i++)
        {
            result += raw[i];
        }

        if (raw.length >= 4)
        {
            result += ") ";
        }

        for (int i = 4; i < raw.length && i < 7; i++)
        {
            result += raw[i];
        }

        if (raw.length >= 7)
        {
            result += "-";
        }

        for (int i = 7; i < raw.length; i++)
        {
            result += raw[i];
        }

        result = (result.length() > 14) ? result.substring(0, 14) : result;

        return (leadingOne) ? ("1 " + result) : result;
    }

    @Override
    public String formatForPost(String number)
    {
        // split incoming into an array
        String split[] = number.split("");
        // remove anything that is not a number
        String result = "";
        for (int i = 0; i < split.length; i++)
        {
            if (isValidInt(split[i]))
            {
                result += split[i];
            }
        }
        return result;
    }

    public static boolean isValidInt(String n)
    {
        try
        {
            Long.parseLong(n);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
