package main.java.helperutilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    public String deviceName;
    public String udid;
    public String platformName;
    public String platformVersion;
    public Boolean skipUnlock;
    public String appPath;
    public Boolean noReset;
    public String remoteURL;
    public String testDataFilePath;

    public ConfigReader() {
        Properties prop = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
            deviceName = prop.getProperty("deviceName");
            udid = prop.getProperty("udid");
            platformName = prop.getProperty("platformName");
            platformVersion = prop.getProperty("platformVersion");
            skipUnlock = Boolean.valueOf(prop.getProperty("skipUnlock"));
            appPath = prop.getProperty("app");
            noReset = Boolean.valueOf(prop.getProperty("noReset"));
            remoteURL = prop.getProperty("remoteURL");
            testDataFilePath = prop.getProperty("testDataFilePath");
            Logger.info("Configuration information has been fetch properly");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Unable to fetch configuration information");
        }

    }

}
