package main.java.dataprovider;

import main.java.helperutilities.ConfigReader;
import main.java.helperutilities.DataReader;
import org.testng.annotations.DataProvider;

public class DataProviderTest {

    static ConfigReader configReader = new ConfigReader();
    static String testDataFilePath = configReader.testDataFilePath;

    @DataProvider(name = "OrderInfo")
    public static Object[][] orderInformation() {
        String test_data_absolut_file_path = System.getProperty("user.dir") + testDataFilePath;
        Object[][] testObjArray = DataReader.getOrderDetails(test_data_absolut_file_path);
        return testObjArray;
    }
}
