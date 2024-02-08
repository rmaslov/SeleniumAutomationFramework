package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utilities.ReadConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
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
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
