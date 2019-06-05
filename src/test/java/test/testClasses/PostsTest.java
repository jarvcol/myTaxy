package test.testClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pojo.AddPostRequestBody;
import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.AddNewPostClient;
import test.clients.BaseClient;
import test.clients.PostByUserClient;

public class PostsTest extends BaseTest{

    private BaseClient client;

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName) throws Exception{
        super.setUsername(userName);
    }

    @Test(dataProvider = "usersId")
    public void getPostByUsersId(String userName, int userId, int expectedPostAmount){
        logger.info("Executing " + "getPostByUsersId " + "URI " + baseUri);
        client = new PostByUserClient(baseUri);

        client.setExpectedResponseCode(200);
        ((PostByUserClient)client).setUserId(userId);
        client.getApiRun();

        logger.info("Response Code " + client.getResponseStatusCode());
        logger.info("Client class " +client.toString());

        int postAmount = client.getApiResponseAsJsonArray().length();

        logger.info("Post done by user: " +postAmount + ", for userName "+userName);

        Assert.assertEquals(postAmount, expectedPostAmount, "Amount of post found is not the expected");
    }

    @Test(dataProvider = "postContent")
    public void addNewPostTest(AddPostRequestBody postContent, int expectedCodeResults){
        logger.info("Executing " + "addNewPostTest " + "URI " + baseUri);
        client = new AddNewPostClient(baseUri);

        client.setExpectedResponseCode(expectedCodeResults);
        ((AddNewPostClient)client).setPostObject(postContent);
        client.getApiRun();

        logger.info("Response Code " + client.getResponseStatusCode());
        logger.info("Client class " +client.toString());


    }
}
