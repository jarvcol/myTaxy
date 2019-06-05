package test.testClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.PostByUserClient;

public class PostsTest extends BaseTest{

    private PostByUserClient client;

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
        client.setUserId(userId);
        client.getApiRun();

        logger.info("Response Code " + client.getResponseStatusCode());
        logger.info("Client class " +client.toString());

        int postAmount = client.getApiResponseAsJsonArray().length();

        logger.info("Post done by user: " +postAmount + ", for userName "+userName);

        Assert.assertEquals(postAmount, expectedPostAmount, "Amount of post found is not the expected");
    }

    @Test(dataProvider = "postContent")
    public void addNewPostTest(Object postContent, int expectedCodeResults){

    }
}
