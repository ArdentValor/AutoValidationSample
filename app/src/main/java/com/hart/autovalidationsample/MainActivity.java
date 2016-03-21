package com.hart.autovalidationsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hart.autovalidation.AutoValidationEditText;
import com.hart.autovalidation.ValidationUtils;

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

        Button submit = (Button) findViewById(R.id.SubmitButton);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean allValid = (firstName.isValid()
                && lastName.isValid()
                && address1.isValid()
                && address2.isValid()
                && phoneNumber.isValid()
                && social.isValid()
                && password.isValid()
                && ValidationUtils.verifyMatch(password.getString(), passwordVerify.getString()));

                String message = (allValid) ? "all fields are valid" : "not all fields are valid";

                Log.i(TAG, message);
            }
        });
    }
}
