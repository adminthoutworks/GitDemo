package org;

import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrderJsonTest extends BaseTest {
        String productName="ZARA COAT 3";
        @Test(dataProvider = "getData",groups = "PurchaseOrder")
        public void submitProductOrderJson(HashMap<String,String> input) throws IOException,InterruptedException {
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

    @Test(dependsOnMethods = {"submitProductOrderJson"})
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
    public Object[][] getData() throws IOException {

            List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/dataFromJson/PurchaseOrder.json");
            return new Object[][] {{data.get(0)},{data.get(1)}};
    }

}