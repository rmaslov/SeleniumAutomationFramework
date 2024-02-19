package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ReadConfig;

public class BaseTest {

    public static WebDriver driver;

    //load webdriver at the start of each session
    @BeforeSuite
    public void setUp() {
        ReadConfig rc = new ReadConfig();

        switch (rc.getProperty("browser")) {
            default -> System.out.println("Wrong browser name");
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
    }

    //close webdriver after each test
    @AfterSuite
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
