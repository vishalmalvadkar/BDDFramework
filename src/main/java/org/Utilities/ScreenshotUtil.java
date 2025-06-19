package org.Utilities;

import org.DriverManager.DriverSetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(String namePrefix) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        File src = ((TakesScreenshot) DriverSetUp.getDriver()).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + namePrefix + "_" + timestamp + ".png";
        try {
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
