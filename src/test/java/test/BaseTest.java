package test;

import org.testng.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import test.clients.BaseClient;
import test.clients.CommentsByPostIdClient;
import test.clients.PostByUserClient;
import test.clients.UsersClient;
import test.utils.JsonUtilities;
import test.utils.PropertiesManager;

import java.util.*;

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
                {"Samantha", ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName), 10}
        };
    }

    @DataProvider(name="postId")
    public Object[][] postIdProvider(){
        String userName = username;

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        int userId = ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName);

        apiClient = new PostByUserClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        return new Object[][]{
                {((PostByUserClient)apiClient).getListOfPostId().get(0), 5}
        };
    }

    @DataProvider(name="commentsOnUsersPosts")
    public Iterator<Object[]> getCommentsOnUserPosts(){
        String userName = username;

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        int userId = ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName);

        apiClient = new PostByUserClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        List<Integer> postsByUser = ((PostByUserClient)apiClient).getListOfPostId();

        apiClient = new CommentsByPostIdClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        List<Object> commentsOnAllPost = new ArrayList<Object>();

        for (Integer postId: postsByUser) {
            ((CommentsByPostIdClient)apiClient).setPostId(postId);
            apiClient.getApiRun();
            commentsOnAllPost.addAll(apiClient.getApiResponseAsJsonArray().toList());
            apiClient.resetRequest();
        }

        return JsonUtilities.getIterableFromList(commentsOnAllPost).iterator();
    }
}