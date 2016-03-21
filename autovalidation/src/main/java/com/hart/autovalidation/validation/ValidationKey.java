package com.hart.autovalidation.validation;

import com.hart.autovalidation.ValidationResponse;

/**
 * Created by Alex on 3/21/16.
 * Proprietary (Hart)
 */
public abstract class ValidationKey
{
    public abstract ValidationResponse isValid(String raw);
}
