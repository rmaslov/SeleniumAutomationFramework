package testCases;

import base.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.SDLoginPage;
import pageObjects.SDProductsPage;
import utilities.ReadConfig;

public class SDProductsTest extends BaseTest {
    SDLoginPage loginPage = new SDLoginPage();
    SDProductsPage productsPage = new SDProductsPage();
    ReadConfig rc = new ReadConfig();

    @BeforeTest
    public void login(){
        loginPage.openPage();
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }
    @Test
    public void addAllItemsToCart(){
        productsPage.addAllItemsToCart();
    }

    @Test
    public void removeAllItemsFromCart(){
        productsPage.removeAllItemsFromCart();
    }

    @Test
    public void addAndRemoveItem(){
        productsPage.addNthItemToCart(2);
        assert productsPage.numberOfItemsInCart() == 1;
        productsPage.removeNthItemToCart(2);
        assert productsPage.numberOfItemsInCart() == 0;
    }

    @Test
    public void checkItemName(){
        assert productsPage.getNthItemName(1).equals(rc.getTestData("first_item_name"));
    }

    @Test
    public void checkItemPrice(){
        assert productsPage.getNthItemPrice(1).equals(rc.getTestData("first_item_price"));
    }
}
