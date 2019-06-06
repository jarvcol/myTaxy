package test.testClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import pojo.PostRequestBody;
import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.*;

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

    @Test(dataProvider = "postToUpdate")
    public void updatePostTest(PostRequestBody postContent, int expectedCodeResults){
        logger.info("Executing " + "updatePostTest " + "URI " + baseUri);
        apiClient = new UpdatePostClient(baseUri);

        apiClient.setExpectedResponseCode(expectedCodeResults);
        ((UpdatePostClient)apiClient).setPostObject(postContent);
        ((UpdatePostClient)apiClient).setPostId(postContent.getPostId());
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        apiClient = new PostByIdClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByIdClient)apiClient).setPostId(postContent.getPostId());
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        softAssertions = new SoftAssert();

        //Following could change and be equal to object input
        softAssertions.assertEquals(((PostByIdClient)apiClient).getPostTitle(),postContent.getTitle(),"Post title did not get updated");
        softAssertions.assertEquals(((PostByIdClient)apiClient).getPostBody(),postContent.getBody(),"Post body did not get update");

        //Assumed that following cannot change. Object input has the original values
        softAssertions.assertEquals(((PostByIdClient)apiClient).getPostId(),postContent.getPostId(),"Post id should not change on update operation");
        softAssertions.assertEquals(((PostByIdClient)apiClient).getUserId(),postContent.getUserId(),"User id should not change on update operation");

        softAssertions.assertAll();
    }


    /*@Test(dataProvider = "postContent")
    public void addNewPostTest(PostRequestBody postContent, int expectedCodeResults){
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

    }*/
}
