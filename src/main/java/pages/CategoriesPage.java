package main.java.pages;

import org.openqa.selenium.By;

public class CategoriesPage extends BasePage {
    By selectedCategoryXpath;
    By productvalue;
    String productChooseResourceID = "com.nopstation.nopcommerce.nopstationcart:id/rvProductList";


    public void clickOnSelectedCategoryButton(String categoryName) {
        selectedCategoryXpath = By.xpath("//android.widget.TextView[@text='" + categoryName + "']");
        waitClick(selectedCategoryXpath, categoryName + " button");
    }

    public void clickOnSelectedProduct(String productName) {
        productvalue = By.xpath("//android.view.ViewGroup/android.widget.TextView[1][@text='" + productName + "']");
        scrollByText(productName,productChooseResourceID, "Scroll by resource id");
    }

}
