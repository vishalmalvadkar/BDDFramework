package stepRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.DriverManager.DriverSetUp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true
)


public class RunnerC extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setBrowser(String browser) {
        DriverSetUp.setBrowser(browser);
    }
}
