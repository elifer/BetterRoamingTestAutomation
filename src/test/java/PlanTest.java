import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.PlanPage;

public class PlanTest extends BaseTest{

    @Test
    public void checkSelectedDestinationPlan() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesIfNecessary();
        homePage.checkHomePage();
        homePage.clickCurrencyItem();
        homePage.selectCurrencyTab();
        homePage.searchAndSelectCurrency("Euro");
        PlanPage planPage = homePage.selectDestination("Thailand");
        planPage.checkSelectedPlanPage("Thailand","5 GB","30 DAYS","Data only", "€9.39");
    }
}
