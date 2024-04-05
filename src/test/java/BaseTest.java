import com.oneglobal.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;
    private String browser;
    private String url;

    @BeforeAll
    void setupAll() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/config.properties"));
        browser = properties.getProperty("browser");
        url = properties.getProperty("url");
        DriverManager.setup(browser);
    }

    @BeforeEach
    void setup(){
        driver = DriverManager.getDriver(browser);
        driver.get(url);
    }

    @AfterEach
    void teardown() {
        if(driver != null){
            driver.quit();
        }
    }

}
