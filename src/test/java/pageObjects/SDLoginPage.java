package pageObjects;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
        driver.get(rc.getProperty("testurl"));
    }

    public void login(String userName, String password) {
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void enterCredentials(String userName, String password) {
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void validateMainHeader(String headerText) {
        assert driver.findElement(mainHeader).getText().equals(headerText);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public boolean isPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
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
        assert driver.findElement(errorMessage).getText().equals(message);
    }

    public void closeErrorMessage(){
        driver.findElement(errorMessage).findElement(By.xpath(".//button")).click();
    }

}
