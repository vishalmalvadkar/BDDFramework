package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.DriverManager.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;

public class SearchStepDef {
    WebDriver driver;
    HomePage hp = new HomePage();

    @Given("User is on homepage")
    public void user_is_on_homepage() {
        driver = DriverSetUp.getDriver();


    }

    @When("User enter {string} in searchbox")
    public void user_enter_in_searchbox(String string) {
        hp.enterSearchText(string);
        hp.searchProuct();
    }

    @Then("User should get all result")
    public void user_should_get_all_result() {
        String tex = hp.getproductResult();
        Assert.assertEquals(tex, "mobile");
    }
}
