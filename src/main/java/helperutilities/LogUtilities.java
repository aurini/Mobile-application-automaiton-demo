package main.java.helperutilities;

public class LogUtilities {
    public static void logMessagesAndAddThemToReport(String message, String status) {
        Logger.info(message);
        switch (status.toLowerCase()) {
            case "pass":
                ExtentReporterManager.logPass(message);
                break;
            case "fail":
                ExtentReporterManager.logFail(message);
                break;
            case "info":
                ExtentReporterManager.logInfo(message);
                break;
            case "warn":
                Logger.warn(message);
                ExtentReporterManager.logWarn(message);
                break;
            case "fatal":
                Logger.fatal(message);
                ExtentReporterManager.logFatal(message);
                break;
            case "error":
                Logger.error(message);
                ExtentReporterManager.logError(message);
                break;
            case "debug":
                Logger.debug(message);
                ExtentReporterManager.logDebug(message);
                break;
        }
    }
    public static void logCreateTest(String testName) {
        Logger.startTestCase(testName);
        ExtentReporterManager.logCreateTest(testName);

    }

}
