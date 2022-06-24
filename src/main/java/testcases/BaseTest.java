package main.java.testcases;

import main.java.driverutility.AndroidDriverUtility;
import main.java.helperutilities.ConfigReader;
import main.java.helperutilities.ExtentReporterManager;
import main.java.helperutilities.LogUtilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    ConfigReader configReader = new ConfigReader();
    static String reportPath;
    static String suiteName;

    @BeforeSuite(alwaysRun = true)
    public void installApplication(ITestContext iTestContext) throws IOException {
        AndroidDriverUtility.startApplication(configReader.deviceName, configReader.udid, configReader.platformName,
                configReader.platformVersion, configReader.skipUnlock, configReader.appPath, configReader.noReset,
                configReader.remoteURL);
        suiteName = iTestContext.getCurrentXmlTest().getSuite().getName();
        ExtentReporterManager.setPathForReportGeneration(suiteName);
        reportPath = ExtentReporterManager.getPathForReportGeneration();
        ExtentReporterManager.generateExtentHtmlReport(reportPath, suiteName);
        LogUtilities.logCreateTest("Test Set Up");
        LogUtilities.logMessagesAndAddThemToReport("TEST EXECUTION STARTED", "info");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(ITestResult results) throws IOException {
        if (ITestResult.FAILURE == results.getStatus()) {
            ExtentReporterManager.logFail(" TC failed");
            String imageName = captureScreenshot();
            ExtentReporterManager.addScreenshot(imageName);
        } else if (ITestResult.SUCCESS == results.getStatus()) {
            ExtentReporterManager.markATestCaseAsPassed("TC passed");
        } else if (ITestResult.SKIP == results.getStatus()) {
            ExtentReporterManager.logWarn("TC ignored");
        }
    }

    private String captureScreenshot() throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
        Date date = new Date();
        String strDate = formatter.format(date);
        File srcfileObj = ((TakesScreenshot) AndroidDriverUtility.getWebDriver()).getScreenshotAs(OutputType.FILE);
        String imageName = strDate + ".png";
        String screenshotPath = reportPath + imageName;
        File DestFileObj = new File(screenshotPath);
        FileUtils.copyFile(srcfileObj, DestFileObj);
        return imageName;

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        AndroidDriverUtility.quitApplication();
        LogUtilities.logCreateTest("Test teardown");
        LogUtilities.logMessagesAndAddThemToReport("TEST EXECUTION ENDED", "info");
        ExtentReporterManager.flushReport();
    }

}
