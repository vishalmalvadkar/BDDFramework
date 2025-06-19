package org.Utilities;

import org.Constant.PathConstants;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    public static String getConfigData(String key)
    {
        Properties prop = new Properties();
        try{
            File file = new File(PathConstants.getConfigPath());
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

        return prop.getProperty(key);
    }
}
