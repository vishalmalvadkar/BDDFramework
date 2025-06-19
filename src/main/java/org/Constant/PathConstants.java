package org.Constant;

import java.io.File;

public class PathConstants {
    private static final String configPath = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"
            +File.separator+"configuration"+File.separator+"Config.properties";

    public static String getConfigPath(){
        return configPath;
    }
}
