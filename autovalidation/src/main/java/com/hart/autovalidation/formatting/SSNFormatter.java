package com.hart.autovalidation.formatting;

import android.util.Log;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class SSNFormatter extends FormattingKey
{
    private static final int LAST_FOUR = 0;
    private static final int FULL_NUMBER = 1;
    private int formatConfig = 1;


    @Override
    public String formatForDisplay(String raw)
    {
        Log.i("SSN", raw);
        raw = raw.replace("-", "");

        if (formatConfig == LAST_FOUR)
        {
            if (raw.length() >= 4)
            {
                raw = raw.substring(0, 4);
            }
        }
        else if (formatConfig == FULL_NUMBER)
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
