package main.java.helperutilities;

import org.apache.logging.log4j.LogManager;

public class Logger {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class.getName());

    public static void startTestCase(String sTestCaseName){
        logger.info("Test Case : "+sTestCaseName+ " starts here");
    }
    public static void endTestCase(String sTestCaseName){
        logger.info("Test Case : "+sTestCaseName+ " ends here");
        logger.info("XXXXXXXXXXX");

    }
    public static void info(String message) {
        logger.info(message);

    }

    public static void warn(String message) {
        logger.warn(message);

    }

    public static void error(String message) {
        logger.error(message);

    }

    public static void fatal(String message) {
        logger.fatal(message);

    }

    public static void debug(String message) {
        logger.debug(message);

    }

}

