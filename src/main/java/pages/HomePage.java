package main.java.pages;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class HomePage extends BasePage{
    //Elements By
    By agreementPageAcceptButtonId = By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAccept");
    By categoriesButtonAccessibilityId = MobileBy.ByAccessibilityId.AccessibilityId("Category");

    public void clickOnAgreementAcceptButton() {

        waitClick(agreementPageAcceptButtonId, "Agreement accept button");
    }

    public void clickOnCategoriesButton()
    {
        waitClick(categoriesButtonAccessibilityId, "Categories button");
    }

}
