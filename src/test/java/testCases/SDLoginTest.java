package testCases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.SDLoginPage;
import utilities.ReadConfig;

public class SDLoginTest extends BaseTest {

    @Test
    public void LoginTest() {
        SDLoginPage loginPage = new SDLoginPage();
        ReadConfig rc = new ReadConfig();

        loginPage.openPage();
        loginPage.validateMainHeader("Swag Labs");
        loginPage.enterCredentials(rc.getProperty("standard_user"), rc.getProperty("password"));
        loginPage.clickLogin();
    }

    @Test
    public void LoginTestLockedOut() throws InterruptedException {
        SDLoginPage loginPage = new SDLoginPage();
        ReadConfig rc = new ReadConfig();

        loginPage.openPage();
        loginPage.validateMainHeader("Swag Labs");
        loginPage.enterCredentials(rc.getProperty("locked_out_user"), rc.getProperty("password"));
        loginPage.clickLogin();
        loginPage.checkIfErrorDisplayed(true);
        loginPage.checkErrorMessage("Epic sadface: Sorry, this user has been locked out.");
        loginPage.closeErrorMessage();
        loginPage.checkIfErrorDisplayed(false);
    }
}
