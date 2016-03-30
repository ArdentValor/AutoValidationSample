package com.hart.autovalidation;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hart.autovalidation.formatting.AddressFormatter;
import com.hart.autovalidation.formatting.CityFormatter;
import com.hart.autovalidation.formatting.CountryFormatter;
import com.hart.autovalidation.formatting.EmailFormatter;
import com.hart.autovalidation.formatting.FormattingKey;
import com.hart.autovalidation.formatting.NameFormatter;
import com.hart.autovalidation.formatting.PasswordFormatter;
import com.hart.autovalidation.formatting.PhoneNumberFormatter;
import com.hart.autovalidation.formatting.RelationshipFormatter;
import com.hart.autovalidation.formatting.SSNFormatter;
import com.hart.autovalidation.formatting.StateFormatter;
import com.hart.autovalidation.formatting.ZipFormatter;
import com.hart.autovalidation.supportutils.GeneralUtils;
import com.hart.autovalidation.validation.AddressValidation;
import com.hart.autovalidation.validation.CityValidation;
import com.hart.autovalidation.validation.CountryValidation;
import com.hart.autovalidation.validation.EmailValidation;
import com.hart.autovalidation.validation.NameValidation;
import com.hart.autovalidation.validation.PasswordValidation;
import com.hart.autovalidation.validation.PhoneNumberValidation;
import com.hart.autovalidation.validation.RelationshipValidation;
import com.hart.autovalidation.validation.SSNValidation;
import com.hart.autovalidation.validation.StateValidation;
import com.hart.autovalidation.validation.ValidationKey;
import com.hart.autovalidation.validation.ZipValidation;

/**
 * Created by Alex on 3/16/16.
 * Proprietary (Hart)
 */
public class AutoValidationEditText extends LinearLayout
{
    private TextInputEditText editText;
    private LinearLayout errorLayout;
    private CheckBox show;
    private TextView errorTextView;

    private AutoValidationEditText verifyLink;

    private static final String NAME = "NAME";
    private static final String ADDRESS = "ADDRESS";
    private static final String PHONE = "PHONE";
    private static final String SSN = "SSN";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String PASSWORD_VERIFY = "PASSWORD_VERIFY";
    private static final String CITY = "CITY";
    private static final String STATE = "STATE";
    private static final String ZIP = "ZIP";
    private static final String COUNTRY = "COUNTRY";
    private static final String RELATIONSHIP = "RELATIONSHIP";

    private String inputType;

    private ValidationKey validationKey;
    private FormattingKey formattingKey;
    private TextInputLayout materialWrapper;

    private boolean isRequired;

    public AutoValidationEditText(final Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoValidationEditText, 0, 0);
        String hintText = a.getString(R.styleable.AutoValidationEditText_hint_text);
        final String errorText = a.getString(R.styleable.AutoValidationEditText_error_text);
        inputType = a.getString(R.styleable.AutoValidationEditText_input_type);
        final int ref = a.getResourceId(R.styleable.AutoValidationEditText_verify_link, 0);
        isRequired = a.getBoolean(R.styleable.AutoValidationEditText_required, true);

        a.recycle();

        if (inputType.equals(PASSWORD_VERIFY))
        {
            // use delayed runnable to avoid null pointer caused by inflation time
            postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    verifyLink = (AutoValidationEditText) ((Activity) context).findViewById(ref);
                    verifyLink.show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                        {
                            if (isChecked)
                            {
                                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                verifyLink.editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            }
                            else
                            {
                                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                verifyLink.editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            }
                        }
                    });
                    // todo consider using an interface to handle events between the two password field objects
                }
            }, 40);
        }

        setOrientation(LinearLayout.VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.auto_validation_edit_text, this, true);

        editText = (TextInputEditText) v.findViewById(R.id.edit_text);
        errorLayout = (LinearLayout) v.findViewById(R.id.layout_error);
        errorTextView = (TextView) v.findViewById(R.id.error_text);

        materialWrapper = (TextInputLayout) findViewById(R.id.text_input_layout);

        setHint(hintText);


        if (inputType.equals(PASSWORD))
        {
            show = (CheckBox) findViewById(R.id.show_cb);
            show.setVisibility(VISIBLE);
            show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked)
                    {
                        editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                    else
                    {
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }
            });
        }

        initializeInputAndKeys(inputType);

        errorTextView.setText(errorText);

        editText.setOnFocusChangeListener(new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    setErrorVisible(context, !isValid());
                }
                else if (hasFocus)
                {
                    if (inputType.equals(STATE))
                    {
                        GeneralUtils.createStatePicker(context, editText, GeneralUtils.EDIT_TEXT).show();
                    }
                    else if (inputType.equals(RELATIONSHIP))
                    {
                        GeneralUtils.createRelationshipPicker(context, editText, GeneralUtils.EDIT_TEXT).show();
                    }
                }
            }
        });

        editText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            //setErrorVisible(getContext(), !isValid());
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            setErrorVisible(getContext(), !isValid());
        }
    };

    private void initializeInputAndKeys(String type)
    {
        switch (type)
        {
            case NAME:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                validationKey = new NameValidation();
                formattingKey = new NameFormatter();
                break;
            case ADDRESS:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                validationKey = new AddressValidation();
                formattingKey = new AddressFormatter();
                break;
            case PHONE:
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
                validationKey = new PhoneNumberValidation();
                formattingKey = new PhoneNumberFormatter();
                break;
            case SSN:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationKey = new SSNValidation();
                formattingKey = new SSNFormatter();
                break;
            case EMAIL:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                validationKey = new EmailValidation();
                formattingKey = new EmailFormatter();
                break;
            case PASSWORD:
            case PASSWORD_VERIFY:
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                validationKey = new PasswordValidation();
                formattingKey = new PasswordFormatter();
                break;
            case CITY:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                validationKey = new CityValidation();
                formattingKey = new CityFormatter();
                break;
            case STATE:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                validationKey = new StateValidation();
                formattingKey = new StateFormatter();
                break;
            case ZIP:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                validationKey = new ZipValidation();
                formattingKey = new ZipFormatter();
                break;
            case COUNTRY:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                validationKey = new CountryValidation();
                formattingKey = new CountryFormatter();
                break;
            case RELATIONSHIP:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                validationKey = new RelationshipValidation();
                formattingKey = new RelationshipFormatter();
                break;
        }
    }

    public boolean isValid()
    {
        if (validate(editText.getText().toString()))
        {
            setErrorVisible(getContext(), false);
            return true;
        }
        else
        {
            setErrorVisible(getContext(), true);
            return false;
        }
    }

    private boolean validate(String raw)
    {
        ValidationResponse response = validationKey.isValid(raw);

        switch (inputType)
        {
            case NAME:
            case PASSWORD:
            case CITY:
            case COUNTRY:
            case STATE:
            case EMAIL:
            case ADDRESS:
            case RELATIONSHIP:
                // nothing special
                break;
            case PASSWORD_VERIFY:
                // handle verification requirements
                String expected = verifyLink.getString();
                String actual = getString();
                response.isValid = expected.equals(actual);
                break;
            case PHONE:
            case ZIP:
            case SSN:
                // handle formatting requirements
                editText.removeTextChangedListener(textWatcher);
                editText.setText(formattingKey.formatForDisplay(raw));
                editText.setSelection(editText.getText().length());
                editText.addTextChangedListener(textWatcher);
                response = validationKey.isValid(getString());
                break;
        }

        if (!isRequired && (getText().length() == 0 || getText() == null))
        {
            return true;
        }

        return (response != null && response.isValid);
    }

    public String getFormattedForDisplay()
    {
        return format(getString());
    }

    public String getFormattedForPost()
    {
        return formattingKey.formatForPost(getString());
    }

    public String getString()
    {
        return getText().toString();
    }

    public Editable getText()
    {
        return editText.getText();
    }

    private String format(String raw)
    {
        return formattingKey.formatForDisplay(raw);
    }

    public void setHint(CharSequence hint)
    {
        materialWrapper.setHint(hint);
    }

    public void setText(CharSequence text)
    {
        editText.setText(text);
    }

    public void setError(CharSequence error)
    {
        errorTextView.setText(error);
    }

    private void setErrorVisible(Context context, boolean visible)
    {
        errorLayout.setVisibility(visible ? VISIBLE : INVISIBLE);
        if (visible)
        {
            editText.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.error_red), PorterDuff.Mode.SRC_IN);
        }
        else
        {
            editText.getBackground().clearColorFilter();
        }
    }
}
