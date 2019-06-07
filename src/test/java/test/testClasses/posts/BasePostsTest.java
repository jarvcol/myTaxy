package test.testClasses.posts;

import org.testng.annotations.DataProvider;
import pojo.PostRequestBody;
import test.BaseTest;
import test.clients.posts.BasePostClient;
import test.clients.users.UsersClient;

public class BasePostsTest extends BaseTest {

    protected BasePostClient postClient;
    protected String username;

    public void setUsername(String username) {
        this.username = username;
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

    @DataProvider(name="postContent")
    public Object[][] postContentGenerator(){
        //Read File, DB, etc
        return new Object[][]{
                {new PostRequestBody("TestPost1","This is the test body 1", 1),201},
                {new PostRequestBody("","This is the test body 2", 1),400},
                {new PostRequestBody("TestPost3","", 1),400},
                {new PostRequestBody("TestPost4","This is the test body 4", -1),400},
                {new PostRequestBody("TestPost1","This is the test body 1", 1),400},
        };
    }

    @DataProvider(name="postToUpdate")
    public Object[][] postUpdateData(){
        //Read File, DB, etc
        return new Object[][]{
                {new PostRequestBody("TestPost1","This is the test body 1", 1, 1),200},
                {new PostRequestBody("","This is the test body 2", 1, 1),400},
                {new PostRequestBody("TestPost3","", 1, 1),400},
                {new PostRequestBody("TestPost4","This is the test body 4", -1, 1),400},
                {new PostRequestBody("TestPost4","This is the test body 4", 1, -1),400}
        };
    }
}
