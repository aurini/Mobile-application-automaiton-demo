package main.java.pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage{

    By quantityFieldId = By.id("com.nopstation.nopcommerce.nopstationcart:id/tvQuantity");
    By sizeButtonXpath = By.xpath("//android.view.ViewGroup[1]/android.widget.TextView[1][@text='Size']");
    By productSizeRadioButtonXpath;
    By doneButtonXpath = By.xpath("//android.widget.TextView[@text='Done']");
    By addToCartId = By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAddToCart");
    String sizeResourceID = "com.nopstation.nopcommerce.nopstationcart:id/tvLayoutTitle";

    public void typeInProductQuantityField(String quantity) {
        sendKeys(quantityFieldId, quantity, true,"Product Quantity");
    }

    public void clickOnSizeButton()
    {
        waitClick(sizeButtonXpath, "Size button");
    }


    public void selectProductSize(String size)
    {
        if (size.toLowerCase().equals("large"))
        {
            productSizeRadioButtonXpath = By.xpath("//android.widget.RadioGroup/android.widget.RadioButton[2]");
        }
        else
        {
            productSizeRadioButtonXpath = By.xpath("//android.widget.RadioGroup/android.widget.RadioButton[1]");
        }
            waitClick(productSizeRadioButtonXpath, size +" radio button");
    }

    public void clickOnDoneButton()
    {
            waitClick(doneButtonXpath,"Done button");
    }

    public void clickOnAddToCart()
    {
            waitClick(addToCartId, "Add to cart");
            //implicitlyWait(10);
    }

    public void scrollDownSizeButton(String menuName)
    {
        scrollByMenuName(menuName, "Scrolling up to "+menuName);
    }

}
