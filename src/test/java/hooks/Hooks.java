package hooks;

import io.cucumber.java.*;
import org.DriverManager.DriverSetUp;
import org.Utilities.ConfigReader;
import org.Utilities.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.reportManager.ExtentReportManager;

import java.time.Duration;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        DriverSetUp.initDriver();
        driver = DriverSetUp.getDriver(); // <-- Proper initialization
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(ConfigReader.getConfigData("BaseUrl"));

    }

    private long stepStartTime;



    @BeforeStep
    public void beforeStep() {
        stepStartTime = System.currentTimeMillis();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        long duration = System.currentTimeMillis() - stepStartTime;
        if (scenario != null && scenario.getStatus() != null) {
            String status = scenario.getStatus().name();
            String stepName = scenario.getName();
            String screenshotPath = ScreenshotUtil.captureScreenshot("Step_" + stepName.replace(" ", "_"));
            ExtentReportManager.logStepWithScreenshot(stepName + " | Duration: " + duration + "ms", status, screenshotPath);
        }
    }

    @After
    public void tearDown() {
        DriverSetUp.quitDriver();  // <-- Use quitDriver to clean thread
    }
}
