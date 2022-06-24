package main.java;

import main.java.driverutility.AndroidDriverUtility;
import main.java.pages.*;

public class PageFactory {
    private static <T> T GetPage(Class<T> className) {
        return org.openqa.selenium.support.PageFactory.initElements(AndroidDriverUtility.getWebDriver(), className);
    }
    public static HomePage getHomePage() {
        return GetPage(HomePage.class);
    }

    public static CategoriesPage getCategoriesPage() {
        return GetPage(CategoriesPage.class);
    }

    public static ProductPage getProductPage() {
        return GetPage(ProductPage.class);
    }
}
