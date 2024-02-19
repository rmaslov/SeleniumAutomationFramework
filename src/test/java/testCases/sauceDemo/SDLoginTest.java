package testCases.sauceDemo;

import base.BaseTest;
import org.testng.annotations.Test;
import pageObjects.sauceDemo.SDLoginPage;
import pageObjects.sauceDemo.SDProductsPage;
import utilities.ReadConfig;

public class SDLoginTest extends BaseTest {
    SDLoginPage loginPage = new SDLoginPage();
    SDProductsPage productsPage = new SDProductsPage();
    ReadConfig rc = new ReadConfig();
    @Test(priority = 1, description = "Login with standard user")
    public void loginTest() {
        loginPage.openPage();
        loginPage.validateMainHeader("Swag Labs");
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }

    @Test(priority = 1, description = "Login with locked out user and check error")
    public void loginTestLockedOut() {
        loginPage.openPage();
        loginPage.enterCredentials(rc.getTestData("locked_out_user"), rc.getTestData("password"));
        loginPage.clickLogin();
        loginPage.checkIfErrorDisplayed(true);
        loginPage.checkErrorMessage("Epic sadface: Sorry, this user has been locked out.");
        loginPage.closeErrorMessage();
        loginPage.checkIfErrorDisplayed(false);
    }

    @Test(priority = 3, description = "Login and logout with problem user", enabled = false)
    public void loginTestProblemUser() throws InterruptedException {
        loginPage.openPage();
        loginPage.login(rc.getTestData("problem_user"), rc.getTestData("password"));
        productsPage.checkNumberOfItems(6);
        productsPage.checkIfImgLinkContains("168b1cce.jpg");
        productsPage.logout();
    }
}
