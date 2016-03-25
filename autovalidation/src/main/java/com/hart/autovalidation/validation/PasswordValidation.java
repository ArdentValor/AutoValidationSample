package com.hart.autovalidation.validation;

import com.hart.autovalidation.configuration.ConfigManager;
import com.hart.autovalidation.configuration.PasswordConfig;
import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public class PasswordValidation extends ValidationKey
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
    private PasswordConfig passwordConfig = null;

    @Override
    public ValidationResponse isValid(String password)
    {
        if (passwordConfig == null)
        {
            passwordConfig = ConfigManager.getPasswordConfig();
        }

        ValidationResponse response = new ValidationResponse();

        // valid length?
        boolean isValid = true;
        boolean validLen = validateLength(password.length(), passwordConfig.minLength, passwordConfig.maxLength);
        boolean patternNum = (!passwordConfig.mustContainNumber || containsNumber(password));
        boolean patternSpecial = (!passwordConfig.mustContainSpecial) || containsSpecialCharacter(password);
        boolean patternLower = (!passwordConfig.mustContainLower || containsLowerCharacter(password));
        boolean patternUpper = (!passwordConfig.mustContainUpper || containsUpperCharacter(password));

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
}
