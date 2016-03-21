package com.hart.autovalidation;

import java.util.ArrayList;

/**
 * Created by Alex on 3/17/16.
 * Proprietary (Hart)
 */
public class ValidationResponse
{
    public boolean isValid;
    public ArrayList<String> responses;

    public ValidationResponse()
    {
        responses = new ArrayList<>();
    }
}
