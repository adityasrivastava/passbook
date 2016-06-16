package com.mds.passkit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mradul on 07/03/16.
 */
public class PassKitUtils {


   public static Properties getProperties() throws IOException
    {
        Properties properties = new Properties();
        properties.load(PassKitUtils.class.getResourceAsStream("/myapplication.properties"));
        return properties;
    }

}
