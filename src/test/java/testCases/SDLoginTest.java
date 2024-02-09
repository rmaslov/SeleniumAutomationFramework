package testCases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.SDLoginPage;
import pageObjects.SDProductsPage;
import utilities.ReadConfig;

public class SDLoginTest extends BaseTest {
    SDLoginPage loginPage = new SDLoginPage();
    SDProductsPage productsPage = new SDProductsPage();
    ReadConfig rc = new ReadConfig();
    @Test
    public void loginTest() {
        loginPage.openPage();
        loginPage.validateMainHeader("Swag Labs");
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }

    @Test
    public void loginTestLockedOut() {
        loginPage.openPage();
        loginPage.enterCredentials(rc.getTestData("locked_out_user"), rc.getTestData("password"));
        loginPage.clickLogin();
        loginPage.checkIfErrorDisplayed(true);
        loginPage.checkErrorMessage("Epic sadface: Sorry, this user has been locked out.");
        loginPage.closeErrorMessage();
        loginPage.checkIfErrorDisplayed(false);
    }

    @Test
    public void loginTestProblemUser()  {
        loginPage.openPage();
        loginPage.login(rc.getTestData("problem_user"), rc.getTestData("password"));
        productsPage.checkNumberOfItems(6);
        productsPage.checkIfImgLinkContains("168b1cce.jpg");
    }
}
