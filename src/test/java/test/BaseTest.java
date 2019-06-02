package test;

import org.testng.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import test.utils.PropertiesManager;

public class BaseTest {

    public static final Logger logger = Logger.getLogger(BaseTest.class);
    public String baseUri;

    @BeforeTest(alwaysRun = true)
    public void beforeSuite() throws Exception{
        PropertyConfigurator.configure("src/test/java/test/resources/log4j.properties");
        PropertiesManager.initializeProperties();
        logger.info("Properties Initialized");

        baseUri = PropertiesManager.getProperty("baseURI");
        logger.info("Based URI = " + baseUri);

    }

    @DataProvider(name="usernames")
    public Object[][] dataProviderExc4(){
        return new Object[][]{
                {"Bret", 1},
                {"Samantha", 3},

        };
    }

}