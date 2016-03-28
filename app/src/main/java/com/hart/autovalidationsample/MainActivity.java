package com.hart.autovalidationsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hart.autovalidation.AutoValidationEditText;

public class MainActivity extends Activity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AutoValidationEditText firstName = (AutoValidationEditText) findViewById(R.id.FirstName);
        final AutoValidationEditText lastName = (AutoValidationEditText) findViewById(R.id.LastName);
        final AutoValidationEditText address1 = (AutoValidationEditText) findViewById(R.id.Address1);
        final AutoValidationEditText address2 = (AutoValidationEditText) findViewById(R.id.Address2);
        final AutoValidationEditText phoneNumber = (AutoValidationEditText) findViewById(R.id.Phone);
        final AutoValidationEditText social = (AutoValidationEditText) findViewById(R.id.Social);
        final AutoValidationEditText password = (AutoValidationEditText) findViewById(R.id.Password);
        final AutoValidationEditText passwordVerify = (AutoValidationEditText) findViewById(R.id.PasswordVerify);
        final AutoValidationEditText city = (AutoValidationEditText) findViewById(R.id.City);
        final AutoValidationEditText state = (AutoValidationEditText) findViewById(R.id.State);
        final AutoValidationEditText country = (AutoValidationEditText) findViewById(R.id.Country);
        final AutoValidationEditText zip = (AutoValidationEditText) findViewById(R.id.Zip);


        Button submit = (Button) findViewById(R.id.SubmitButton);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validFirstName = firstName.isValid();
                boolean validLastName = lastName.isValid();
                boolean validAddress1 = address1.isValid();
                boolean validAddress2 = address2.isValid();
                boolean validPhoneNumber = phoneNumber.isValid();
                boolean validSocial = social.isValid();
                boolean validPassword = password.isValid();
                boolean validVerify = passwordVerify.isValid();
                boolean validCity = city.isValid();
                boolean validState = state.isValid();
                boolean validCountry = country.isValid();
                boolean validZip = zip.isValid();



                boolean allValid = (validFirstName
                && validLastName
                && validAddress1
                && validAddress2
                && validPhoneNumber
                && validSocial
                && validPassword
                && validVerify
                && validCity
                && validState
                && validCountry
                && validZip);

                String message = (allValid) ? "all fields are valid" : "not all fields are valid";

                Log.i(TAG, message);
            }
        });
    }
}
