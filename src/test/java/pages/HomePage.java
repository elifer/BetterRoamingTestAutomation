package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HomePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    private static final By cookieDialogSelector = By.xpath("//div[@id='usercentrics-root']");
    WebDriver driver;
    @FindBy(xpath = "//div[1]/div[2]/astro-island[1]/div[1]/div[2]/div[1]")
    private WebElement currencyItem;
    @FindBy(xpath = "//button[contains(text(),'Currency')]")
    private WebElement currencyTabSelector;
    @FindBy(xpath = "//header/div[1]/a[1]/span[1]/img[1]")
    private WebElement betterRoamingLogo;
    @FindBy(xpath = "//div[2]/div[1]/div[1]/input[1]")
    private WebElement currencySearchBar;
    @FindBy(xpath = "//div[3]/div[1]/div[3]/div[1]")
    private WebElement selectedCurrency;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickCurrencyItem() {
        waitForVisibility(currencyItem,10);
        currencyItem.click();
        logger.info("Currency item is clicked.");
    }
    public void selectCurrencyTab() {
        waitForVisibility(currencyTabSelector,10);
        currencyTabSelector.click();
        logger.info("Currency tab is selected.");
    }

    private void waitForVisibility(WebElement element, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(timeoutInSecond, ChronoUnit.SECONDS ));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void checkHomePage() {
        Assertions.assertTrue(betterRoamingLogo.isDisplayed(),"Better Roaming home page is not opened.");
    }

    public void searchAndSelectCurrency(String currency) {
        waitForVisibility(currencySearchBar,5);
        currencySearchBar.sendKeys(currency);
        waitForVisibility(selectedCurrency,5);
        selectedCurrency.click();
        logger.info(currency + " is selected.");
    }

    public void acceptCookiesIfNecessary() {
        SearchContext shadowRoot = driver.findElement(cookieDialogSelector).getShadowRoot();

        WebElement acceptAllButton = shadowRoot.findElement(By.cssSelector("button[data-testid='uc-accept-all-button']"));
        if (acceptAllButton.isDisplayed()){
            acceptAllButton.click();
        }
    }

    public PlanPage selectDestination(String destination) {
        String destinationSelectorString = "//p[contains(text(),'%s')]".formatted(destination);
        WebElement selectedDestination = driver.findElement(By.xpath(destinationSelectorString));
        waitForVisibility(selectedDestination, 5);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", selectedDestination);
        logger.info(destination + " is selected.");
        return new PlanPage(driver);
    }
}
