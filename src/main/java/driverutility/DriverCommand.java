package main.java.driverutility;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import main.java.helperutilities.LogUtilities;
import main.java.helperutilities.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DriverCommand {

    final AndroidDriver driver;
    final static int waitTime = 2;

    public DriverCommand() {
        this.driver = AndroidDriverUtility.getWebDriver();

    }

    public WebDriverWait wait(int waitTime) {
        try {
            return new WebDriverWait(driver, waitTime);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Boolean isElementVisible(By locator) {
        try {
            wait(15).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            Logger.error("Unable to locate the element " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Element is not visible ", "fail");
            Assert.fail();
        }
        return false;
    }

    public void waitClick(By locator, String message) {
        try {
            if (isElementVisible(locator)) {
                driver.findElement(locator).click();
                LogUtilities.logMessagesAndAddThemToReport(message + " clicked", "pass");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Logger.error("Unable to click the button " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport(message + "is not clicked", "fail");
            Assert.fail();
        }
    }


    public String getAttribute(By locator, String attributeName, String logMessage) {
        try {
            if (isElementVisible(locator)) {
                String valueOfAttribute = driver.findElement(locator).getAttribute(attributeName);
                LogUtilities.logMessagesAndAddThemToReport("Attribute value of " + attributeName + " of " +
                        logMessage + " is returned", "pass");
                return valueOfAttribute;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Logger.error("Unable to get attribute " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("unable to get attribute value", "fail");
            Assert.fail();
        }
        return null;
    }

    public void clearText(By locator, String logMessage) {
        try {
            driver.findElement(locator).clear();
            LogUtilities.logMessagesAndAddThemToReport(logMessage + " text is cleared", "pass");
        } catch (Exception exception) {
            Logger.error("Unable to clear text " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Unable to clear text", "fail");
            exception.printStackTrace();
            Assert.fail();
        }
    }

    public void sendKeys(By locator, String givenTextValue, Boolean clearText, String logMessage) {
        try {
            if (isElementVisible(locator)) {
                if (clearText) {
                    this.clearText(locator, logMessage);
                }
                LogUtilities.logMessagesAndAddThemToReport(logMessage + " is typed", "pass");
                driver.findElement(locator).sendKeys(givenTextValue);
            }

        } catch (Exception exception) {
            Logger.error("Unable to type " + givenTextValue + " in " + logMessage + " exception " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Unable to type value", "fail");
            exception.printStackTrace();
            Assert.fail();
        }
    }


    public void scrollByMenuName(String menuText, String logMessage) {
        try{
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." +
                    "instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + menuText + "\").instance(0));")).click();
            LogUtilities.logMessagesAndAddThemToReport(logMessage , "pass");
        }
        catch (Exception exception) {
            Logger.error("Unable to scroll. exception " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Unable to scroll", "fail");
            exception.printStackTrace();
            Assert.fail();
        }

    }

    public void scrollByText(String menuText, String resourceID, String logMessage) {
        try
        {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\""+resourceID+"\")).getChildByText("
                            + "new UiSelector().className(\"android.widget.TextView\"), \""+menuText+"\")")).click();
            LogUtilities.logMessagesAndAddThemToReport(logMessage , "pass");
        }
        catch (Exception exception) {
            Logger.error("Unable to scroll. exception " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Unable to scroll", "fail");
            exception.printStackTrace();
            Assert.fail();
        }

    }

    public Boolean isToastMessageDisplayed(String toastMessage, String logMessage) {
        try
        {
            String xmlFormat = driver.getPageSource();
            if(xmlFormat.contains(toastMessage)) {
                System.out.println("Toast message displayed: " + toastMessage);
                LogUtilities.logMessagesAndAddThemToReport(logMessage , "pass");
                return true;
            }

        }
        catch (Exception exception) {
            Logger.error("Unable to find toast message " + exception.getMessage());
            LogUtilities.logMessagesAndAddThemToReport("Unable to find toast message", "fail");
            exception.printStackTrace();
            Assert.fail();
        }
        return false;
    }

}