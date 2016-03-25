package com.hart.autovalidation.configuration;

/**
 * Created by Alex on 3/24/16.
 * Proprietary (Hart)
 */
public class ConfigManager
{
    private static PasswordConfig passwordConfig = null;
    private static NameConfig nameConfig = null;
    private static SSNConfig ssnConfig = null;


    // password
    public static PasswordConfig getPasswordConfig()
    {
        return (passwordConfig == null) ? new PasswordConfig() : passwordConfig;
    }

    public static void setPasswordConfig(PasswordConfig config)
    {
        passwordConfig = config;
    }

    // name
    public static NameConfig getNameConfig()
    {
        return (nameConfig == null) ? new NameConfig() : nameConfig;
    }

    public static void setNameConfig(NameConfig config)
    {
        nameConfig = config;
    }

    // SSN
    public static SSNConfig getSSNConfig()
    {
        return (ssnConfig == null) ? new SSNConfig() : ssnConfig;
    }

    public static void setSSNConfig(SSNConfig config)
    {
        ssnConfig = config;
    }
}
