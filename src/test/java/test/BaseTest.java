package test;

import org.testng.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.asserts.SoftAssert;
import test.clients.BaseClient;

public class BaseTest {

    public static final Logger logger = Logger.getLogger(BaseTest.class);
    protected String baseUri;
    protected BaseClient apiClient;
    protected SoftAssert softAssertions;

    public BaseTest(){
    }

    @Parameters({ "baseUri" })
    @BeforeClass(alwaysRun = true)
    public void beforeMethods(String baseUri){
        PropertyConfigurator.configure("src/test/java/test/resources/log4j.properties");
        logger.info("Properties Initialized for thread "+Thread.currentThread().getId());

        this.baseUri = baseUri;
        logger.info("Based URI = " + baseUri);
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        //TODO
        //Erase all created and/or rollback any changed data
        System.out.println("After test-method. Thread id is: " + +Thread.currentThread().getId());
    }
}