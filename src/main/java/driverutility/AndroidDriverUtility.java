package main.java.driverutility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.helperutilities.LogUtilities;
import main.java.helperutilities.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverUtility {
    private static AndroidDriver driver;
    static String working_directory = null;

    public static void startApplication(String deviceName, String udidValue, String platformName, String platformVersion,
                                        Boolean skipUnlockValue, String appPath, Boolean noResetValue, String remoteURL) throws MalformedURLException {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            working_directory = System.getProperty("user.dir");
            String apk_absolute_path = working_directory + appPath;
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("udid", udidValue); //DeviceId from "adb devices" command
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("skipUnlock", skipUnlockValue);
            caps.setCapability("app", apk_absolute_path);
            caps.setCapability("noReset", noResetValue);
            driver = new AndroidDriver(new URL(remoteURL), caps);
            Logger.info("Application has been started");
        } catch (MalformedURLException e) {
            Logger.error("Unable to start the application");
            Logger.error(e.getMessage());
            Assert.fail();
            e.printStackTrace();
        }
    }

    public static AndroidDriver getWebDriver() {
        return driver;
    }

    public static void quitApplication() {
        try {
            driver.quit();
            Logger.info("Application has been closed");
        } catch (Exception exception) {
            Logger.error("Unable to close the application");
            Logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
