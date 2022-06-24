package main.java.pages;

import main.java.driverutility.DriverCommand;
import org.openqa.selenium.By;

public class BasePage extends DriverCommand {
    DriverCommand driverCommand;

    public BasePage() {
        driverCommand = new DriverCommand();
    }

    public String getAlterModalTextMessage(By locator, String attributeName) {
        return driverCommand.getAttribute(locator, attributeName, "Alert modal type");
    }

    public void clickOnOkayBtnAlertModal(By locator, String logMessage) {
        driverCommand.waitClick(locator, logMessage);
    }

    public String getAlterModalTextType(By locator, String attributeName) {
        return driverCommand.getAttribute(locator, attributeName, "Alert modal text");
    }

}
