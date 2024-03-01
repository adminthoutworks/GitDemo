package org;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends BaseTest {
        String productName="ZARA COAT 3";
        @Test(dataProvider = "getData",groups = "PurchaseOrder")
        public void submitProductOrder(HashMap<String,String> input) throws IOException,InterruptedException {
        //user-john.smith@example.com
        //password- testUser@123
        ProductCatlogPage productCatlogPage=landingPage.loginApplication(input.get("email"),input.get("password"));
        //get product list
        List<WebElement>products=productCatlogPage.getProductList();
        //Add item to cart
        productCatlogPage.addProductToCart(input.get("prodName"));
        //click on cart button
        CartPage cartPage=productCatlogPage.goToCartPage();
        //Go to cart page
        Boolean match =cartPage.verifyProductDisplay(input.get("prodName"));
        Assert.assertTrue(match);
        //click on checkout button
        CheckOutPage checkOutPage =cartPage.goToCheckout();
        checkOutPage.setSelectCountry("India");
        ConfirmationPage confirmationPage=checkOutPage.submitOrder();
        String msg=confirmationPage.getConfirmationMsg();
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitProductOrder"})
    public void orderHistoryTest(){
                ProductCatlogPage productCatlogPage=landingPage.loginApplication("john.smith@example.com","testUser@123");
                OrderPage orderPage=productCatlogPage.goToOrderPage();
                Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

    }


    //with below method also we can try to send data to test
//    @DataProvider
//    public Object[][] getData(){
//
//        return new Object[][] {{"john.smith@example.com","testUser@123","ZARA COAT 3"},{"angela.gross@testuser.com","testUser@123","ADIDAS ORIGINAL"}};
//    }

    //Now use hasmap to send the data
        @DataProvider
    public Object[][] getData(){

            HashMap<String,String> map=new HashMap<String,String>();
            map.put("email","john.smith@example.com");
            map.put("password","testUser@123");
            map.put("prodName","ZARA COAT 3");

            HashMap<String,String> map1=new HashMap<String,String>();
            map1.put("email","angela.gross@testuser.com");
            map1.put("password","testUser@123");
            map1.put("prodName","ADIDAS ORIGINAL");
        return new Object[][] {{map},{map1}};
    }

}