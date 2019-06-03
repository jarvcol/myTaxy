package test;

import org.testng.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import test.clients.BaseClient;
import test.clients.PostByUserClient;
import test.clients.UsersClient;
import test.utils.JsonUtilities;
import test.utils.PropertiesManager;

public class BaseTest {

    public static final Logger logger = Logger.getLogger(BaseTest.class);
    public String baseUri;
    private BaseClient apiClient;
    private String username;

    public BaseTest(){
    }

    public BaseTest(String username){
        this.username = username;
    }

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() throws Exception{
        PropertyConfigurator.configure("src/test/java/test/resources/log4j.properties");
        PropertiesManager.initializeProperties();
        logger.info("Properties Initialized for thread "+Thread.currentThread().getId());

        baseUri = PropertiesManager.getProperty("baseURI");
        logger.info("Based URI = " + baseUri);

    }

    @AfterClass
    public void afterMethod() {
        //TODO
        //Erase all created and/or rollback any changed data
        System.out.println("After test-method. Thread id is: " + +Thread.currentThread().getId());
    }

    @DataProvider(name="userNames")
    public Object[][] userNamesProvider(){
        return new Object[][]{
                {"Bret", 1},
                {"Samantha", 3}
        };
    }

    @DataProvider(name="usersId")
    public Object[][] usersIdProvider(){
        String userName = "Samantha";

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        return new Object[][]{
                {"Samantha", JsonUtilities.getUserIdByUserNameFromUserList(userName, apiClient.getApiResponseAsJsonArray()), 10}
        };
    }

    @DataProvider(name="postId")
    public Object[][] postIdProvider(){
        String userName = username;

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        int userId = JsonUtilities.getUserIdByUserNameFromUserList(userName, apiClient.getApiResponseAsJsonArray());

        apiClient = new PostByUserClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        return new Object[][]{
                {apiClient.getApiResponseAsJsonArray().getJSONObject(0).getInt("id"), 5}
        };
    }
}