package com.hart.autovalidation.supportutils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Alex on 3/29/16.
 * Proprietary (Hart)
 */
public class GeneralUtils
{
    public static final int TEXT_VIEW = 0;
    public static final int EDIT_TEXT = 1;
    public static String[] STATES_ARRAY = {
            "AK",
            "AL",
            "AZ",
            "AR",
            "CA",
            "CO",
            "CT",
            "DE",
            "FL",
            "GA",
            "HI",
            "ID",
            "IL",
            "IN",
            "IA",
            "KS",
            "KY",
            "LA",
            "ME",
            "MD",
            "MA",
            "MI",
            "MN",
            "MS",
            "MO",
            "MT",
            "NE",
            "NV",
            "NH",
            "NJ",
            "NM",
            "NY",
            "NC",
            "ND",
            "OH",
            "OK",
            "OR",
            "PA",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VT",
            "VA",
            "WA",
            "WV",
            "WI",
            "WY"
    };

    public static String[] RELATIONS_ARRAY = {
            "Grandfather",
            "Grandmother",
            "Father",
            "Mother",
            "Brother",
            "Sister",
            "Significant Other",
            "Friend",
            "Self",
            "Other"
    };

    public static AlertDialog createStatePicker(Context context, final View view, final int type)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Please select a state.");
        builder.setItems(STATES_ARRAY, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int item)
            {
                switch (type)
                {
                    case TEXT_VIEW:
                        ((TextView) view).setText(STATES_ARRAY[item]);
                        break;
                    case EDIT_TEXT:
                        ((EditText) view).setText(STATES_ARRAY[item]);
                        break;
                }
            }
        });

        AlertDialog alert = builder.create();

        return alert;
    }

    public static AlertDialog createRelationshipPicker(Context context, final View view, final int type)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Please select a relationship type.");
        builder.setItems(RELATIONS_ARRAY, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int item)
            {
                switch (type)
                {
                    case TEXT_VIEW:
                        ((TextView) view).setText(RELATIONS_ARRAY[item]);
                        break;
                    case EDIT_TEXT:
                        ((EditText) view).setText(RELATIONS_ARRAY[item]);
                        break;
                }
            }
        });

        AlertDialog alert = builder.create();

        return alert;
    }
}
