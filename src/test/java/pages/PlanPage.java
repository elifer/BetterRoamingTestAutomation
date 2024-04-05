package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.jupiter.api.Assertions;
public class PlanPage {

    private static final Logger logger = LogManager.getLogger(PlanPage.class);
    WebDriver driver;
    public PlanPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")
    private WebElement countryText;
    @FindBy(xpath = "//div[2]/div[2]/div[1]/div[1]/div[2]/p[1]")
    private WebElement dataText;
    @FindBy(xpath = "//div[2]/div[2]/div[1]/div[1]/div[3]/p[1]")
    private WebElement validText;
    @FindBy(xpath = "//div[2]/div[2]/div[1]/div[1]/div[4]/p[1]")
    private WebElement planTypeText;
    @FindBy(xpath = "//div[3]/div[2]/div[2]/div[1]/div[1]/p[1]")
    private WebElement priceText;
    public void checkSelectedPlanPage(String country, String data, String valid, String planType, String price) {
        Assertions.assertTrue(countryText.getText().equals(country));
        Assertions.assertTrue(dataText.getText().equals(data));
        Assertions.assertTrue(validText.getText().equals(valid));
        Assertions.assertTrue(planTypeText.getText().equals(planType));
        Assertions.assertTrue(priceText.getText().equals(price));
        logger.info(country+" plan is checked.");
    }
}
