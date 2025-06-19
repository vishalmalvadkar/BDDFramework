package pages;

import org.DriverManager.DriverSetUp;
import org.Utilities.ElementAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(DriverSetUp.getDriver(), this);
    }

    @FindBy(name = "q")
    private WebElement searchbar;

    @FindBy(xpath = "//input[@name = 'q']/parent::div/preceding-sibling::button")
    private  WebElement searchButton;

    @FindBy(xpath = "//span[text() = 'mobile']")
    private WebElement searchedProduct;

    public void enterSearchText(String productName)
    {
        ElementAction.type(searchbar, productName);
    }

    public void searchProuct(){
        ElementAction.click(searchButton);
    }

    public String getproductResult(){
        return ElementAction.getText(searchedProduct);
    }
}
