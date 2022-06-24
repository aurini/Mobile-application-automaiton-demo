package main.java.testcases;

import main.java.PageFactory;
import main.java.dataprovider.DataProviderTest;
import main.java.helperutilities.LogUtilities;
import main.java.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    HomePage homePage;
    CategoriesPage categoriesPage;
    ProductPage productPage;

    @Test(dataProvider = "OrderInfo", dataProviderClass = DataProviderTest.class)
    public void checkIfUserCanAddItemToCart(String category, String productName, String productSize, String productQuantity) {

        LogUtilities.logCreateTest("Add to cart test");

        homePage = PageFactory.getHomePage();
        categoriesPage = PageFactory.getCategoriesPage();
        productPage = PageFactory.getProductPage();

        homePage.clickOnAgreementAcceptButton();
        homePage.clickOnCategoriesButton();
        categoriesPage.clickOnSelectedCategoryButton(category);
        categoriesPage.clickOnSelectedProduct(productName);
        productPage.scrollDownSizeButton("Size");
        productPage.selectProductSize(productSize);
        productPage.clickOnDoneButton();
        productPage.typeInProductQuantityField(productQuantity);
        productPage.clickOnAddToCart();

        Assert.assertTrue(productPage.isToastMessageDisplayed("The product has been added to your shopping cart",
                "Toast message captured"), "Toast Message did not appear");

    }

}
