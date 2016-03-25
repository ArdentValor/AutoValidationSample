package com.hart.autovalidation;

import com.hart.autovalidation.configuration.PasswordConfig;

import java.util.ArrayList;

/**
 * Created by Alex on 3/16/16.
 * Proprietary (Hart)
 */
public class ValidationUtils
{
    // password validation
    private static final String REGEX_CONTAINS_NUMBER = ".*\\d+.*";
    private static final String REGEX_CONTAINS_SPECIAL = "[A-Za-z0-9 ]*";
    private static final String REGEX_CONTAINS_LOWER = ".*[a-z].*";
    private static final String REGEX_CONTAINS_UPPER = ".*[A-Z].*";
    //private static final String PASSWORD_ERROR_1 = "password must contain between 8 and 15 characters";
    private static final String PASSWORD_ERROR_2 = "password must contain at least one numeric character";
    private static final String PASSWORD_ERROR_3 = "password must contain at least one lowercase character";
    private static final String PASSWORD_ERROR_4 = "password must contain at least one uppercase character";
    private static final String PASSWORD_ERROR_5 = "password must contain at least one special character";
    private static final String PASSWORD_IS_VALID = "password is valid";

    private static PasswordConfig passwordConfig = null;

    // personal name validation
    private static final int NAME_CHARACTER_MIN = 1;

    // password validation
    public static void setPasswordConfig(PasswordConfig config)
    {
        passwordConfig = config;
    }

    public static ValidationResponse validatePassword(String password)
    {
        if (passwordConfig == null)
        {
            setPasswordConfig(new PasswordConfig());
        }

        ValidationResponse response = new ValidationResponse();

        // valid length?
        boolean isValid = true;
        boolean validLen = validateLength(password.length(), passwordConfig.minLength, passwordConfig.maxLength);
        boolean patternNum = (!passwordConfig.mustContainNumber || containsNumber(password));
        boolean patternSpecial = (!passwordConfig.mustContainSpecial) || containsSpecialCharacter(password);
        boolean patternLower = (!passwordConfig.mustContainLower || containsLowerCharacter(password));
        boolean patternUpper = (!passwordConfig.mustContainUpper || containsUpperCharacter(password));

        response.responses = new ArrayList<>();

        if (!validLen)
        {
            isValid = false;
            response.responses.add("password must contain between " + passwordConfig.minLength + " and " + passwordConfig.maxLength + " characters");
        }

        if (!patternNum)
        {
            isValid = false;
            response.responses.add(PASSWORD_ERROR_2);
        }

        if (!patternLower)
        {
            isValid = false;
            response.responses.add(PASSWORD_ERROR_3);
        }

        if (!patternUpper)
        {
            isValid = false;
            response.responses.add(PASSWORD_ERROR_4);
        }

        if (!patternSpecial)
        {
            isValid = false;
            response.responses.add(PASSWORD_ERROR_5);
        }

        if (isValid)
        {
            response.responses.add(PASSWORD_IS_VALID);
        }

        response.isValid = isValid;

        return response;
    }


    private static boolean validateLength(int length, int minLength, int maxLength)
    {
        return (length >= minLength && length <= maxLength);
    }

    private static boolean containsNumber(String password)
    {
        return (password != null && password.matches(REGEX_CONTAINS_NUMBER));
    }

    private static boolean containsSpecialCharacter(String password)
    {
        return (password != null && !password.matches(REGEX_CONTAINS_SPECIAL));
    }

    private static boolean containsLowerCharacter(String password)
    {
        return (password != null && password.matches(REGEX_CONTAINS_LOWER));
    }

    private static boolean containsUpperCharacter(String password)
    {
        return (password != null && password.matches(REGEX_CONTAINS_UPPER));
    }

    public static boolean verifyMatch(String password1, String password2)
    {
        return password1.equals(password2);
    }


    // personal name validation
    public static ValidationResponse validatePersonalName(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        response.isValid = raw.length() >= NAME_CHARACTER_MIN;
        response.responses = new ArrayList<>();
        response.responses.add((response.isValid) ? "name is valid" : "must be a valid name");
        return response;
    }

    // phone number validation
    public static ValidationResponse validatePhoneNumber(String raw)
    {
        ValidationResponse response = new ValidationResponse();
        response.isValid = isValidNumber(raw);
        response.responses = new ArrayList<>();
        response.responses.add((response.isValid) ? "valid number" : "please provide a valid 10 digit number");

        return response;
    }


    public static boolean isValidNumber(String number)
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
        // ensure that it is a 10 digit number
        return ((result.length() == 10  && !result.substring(0,1).equals("1")) || (result.length() == 11  && result.substring(0,1).equals("1")));
    }

    /**
     * Alex (oct/9/2015)
     * @param n string that might be an int
     * @return true if n is an int
     */
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
