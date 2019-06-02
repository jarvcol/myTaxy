package test.testClasses;

import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.testData.BaseClient;
import test.testData.PostByUserClient;

public class PostsTest extends BaseTest{

    private PostByUserClient client;

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
}
