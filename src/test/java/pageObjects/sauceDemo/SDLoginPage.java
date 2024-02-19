package pageObjects.sauceDemo;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utilities.LoggerUtils;
import utilities.ReadConfig;

public class SDLoginPage extends BaseTest {
    //Inputs
    private final By userNameTextBox = By.id("user-name");
    private final By passwordTextBox = By.id("password");

    //Buttons
    private final By loginButton = By.id("login-button");

    //Labels
    private final By mainHeader = By.className("login_logo");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");


    ReadConfig rc = new ReadConfig();


    public void openPage() {
        LoggerUtils.info("Opening " + rc.getProperty("testurl"));
        driver.get(rc.getProperty("testurl"));
    }

    public void login(String userName, String password) {
        LoggerUtils.info("Log in with user " + userName);
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void enterCredentials(String userName, String password) {
        LoggerUtils.info("Enter credentials for user " + userName);
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void validateMainHeader(String headerText) {
        LoggerUtils.info("Checking page header");
        assert driver.findElement(mainHeader).getText().equals(headerText);
    }

    public void clickLogin(){
        LoggerUtils.info("Click login button");
        driver.findElement(loginButton).click();
    }

    public boolean isPresent(By locator) {
        try {
            LoggerUtils.info("Checking that element ["+locator+"] is displayed");
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            LoggerUtils.info("Checking that element ["+locator+"] is not displayed");
            return false;
        }
    }
    public void checkIfErrorDisplayed(Boolean isDisplayed){
        if (isDisplayed){
            assert isPresent(errorMessage);
        } else {
            assert !isPresent(errorMessage);
        }
    }

    public void checkErrorMessage(String message){
        LoggerUtils.info("Checking error message ["+message+"] is displayed");
        assert driver.findElement(errorMessage).getText().equals(message);
    }

    public void closeErrorMessage(){
        LoggerUtils.info("Close error message");
        driver.findElement(errorMessage).findElement(By.xpath(".//button")).click();
    }

}
