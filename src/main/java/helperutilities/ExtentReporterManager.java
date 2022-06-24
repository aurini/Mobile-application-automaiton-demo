package main.java.helperutilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterManager {

    private static ExtentReports extentReports;
    private static ExtentTest logger;
    private static String reportPath;

    public static void setPathForReportGeneration(String folderName) {
        Format rf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String fullStamp = rf.format(new Date());
        reportPath = System.getProperty("user.dir") + "\\TestReports\\Reports_" + folderName + "\\" + fullStamp + "\\";
    }

    public static String getPathForReportGeneration() {
        return reportPath;
    }

    public static void generateExtentHtmlReport(String path, String reportName) {
        String reportPath = path + reportName + ".html";
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setAutoCreateRelativePathMedia(true);
        extentHtmlReporter.config().setDocumentTitle(reportName);
        extentHtmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\main\\resources\\" +
                "extent-config.xml");
    }

    public static void logFail(String message) {
        logger.log(Status.FAIL, message);

    }

    public static void logCreateTest(String testName) {
        logger = extentReports.createTest(testName);
    }

    public static void markATestCaseAsPassed(String message) {
        logger.pass(message);
    }

    public static void logWarn(String message) {
        logger.log(Status.WARNING, message);

    }

    public static void logPass(String message) {
        logger.log(Status.PASS, message);

    }

    public static void logFatal(String message) {
        logger.log(Status.FATAL, message);

    }

    public static void logError(String message) {
        logger.log(Status.ERROR, message);

    }

    public static void logDebug(String message) {
        logger.log(Status.DEBUG, message);

    }

    public static void logInfo(String message) {
        logger.log(Status.INFO, message);
    }

    public static void flushReport() {
        extentReports.flush();

    }

    public static void addScreenshot(String imageName) throws IOException {
        logger.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(imageName).build());
    }
}
