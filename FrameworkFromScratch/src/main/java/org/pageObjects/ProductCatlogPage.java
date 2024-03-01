package org.pageObjects;

import org.abstractComponents.AbstractComponets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatlogPage extends AbstractComponets {

    WebDriver driver;
    public ProductCatlogPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
     }
     @FindBy(css=".mb-3")
     List<WebElement> products;

    By productBy= By.cssSelector(".mb-3");
    By addToCart=By.cssSelector(".card-body button:last-of-type");

    By toastMsg=By.cssSelector("#toast-container");

    By toastMsgToDisaapear=By.cssSelector(".ng-animating");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod=getProductList().stream().filter(product -> product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProductToCart(String productName){
        WebElement prod=getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMsg);
        waitForElementToAppear(toastMsgToDisaapear);
    }



}
