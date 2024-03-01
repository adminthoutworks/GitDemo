package org.pageObjects;

import org.abstractComponents.AbstractComponets;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponets {

    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
     }

     @FindBy(css = ".totalRow button")
    WebElement checkoutElement;

    @FindBy(css =".cartSection h3")
    List<WebElement> productTitles;

    public boolean verifyProductDisplay(String productName){
        boolean match=productTitles.stream().anyMatch(cartSelect -> cartSelect.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckout(){
        checkoutElement.click();
        return new CheckOutPage(driver);

    }


}
