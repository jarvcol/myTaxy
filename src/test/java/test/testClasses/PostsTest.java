package test.testClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pojo.AddPostRequestBody;
import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.AddNewPostClient;
import test.clients.BaseClient;
import test.clients.PostByIdClient;
import test.clients.PostByUserClient;

public class PostsTest extends BaseTest{

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName) throws Exception{
        super.setUsername(userName);
    }

    @Test(dataProvider = "usersId")
    public void getPostByUsersId(String userName, int userId, int expectedPostAmount){
        logger.info("Executing " + "getPostByUsersId " + "URI " + baseUri);
        apiClient = new PostByUserClient(baseUri);

        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        int postAmount = apiClient.getApiResponseAsJsonArray().length();

        logger.info("Post done by user: " +postAmount + ", for userName "+userName);

        Assert.assertEquals(postAmount, expectedPostAmount, "Amount of post found is not the expected");
    }

    @Test(dataProvider = "postContent")
    public void addNewPostTest(AddPostRequestBody postContent, int expectedCodeResults){
        logger.info("Executing " + "addNewPostTest " + "URI " + baseUri);
        apiClient = new AddNewPostClient(baseUri);

        apiClient.setExpectedResponseCode(expectedCodeResults);
        ((AddNewPostClient)apiClient).setPostObject(postContent);
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        int newPostId = apiClient.getResponseStatusCode();

        apiClient = new PostByIdClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByIdClient)apiClient).setPostId(newPostId);
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        Assert.assertTrue(((PostByIdClient)apiClient).checkPostUserId(postContent.getUserId()), "Post was supposed to be done but not found");

    }
}
