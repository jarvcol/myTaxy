package test.testClasses.posts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import pojo.PostRequestBody;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.posts.*;

public class PostsTest extends BasePostsTest {

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName) throws Exception{
        super.setUsername(userName);
    }

    @Test(dataProvider = "usersId", groups = {"greenTest"})
    public void getPostByUsersId(String userName, int userId, int expectedPostAmount){
        logger.info("Executing " + "getPostByUsersId " + "URI " + baseUri);
        postClient = new PostByUserClient(baseUri);

        postClient.setExpectedResponseCode(200);
        postClient.setUserId(userId);
        postClient.getApiRun();

        logger.info("Response Code " + postClient.getResponseStatusCode());
        logger.info("Client class " +postClient.toString());

        int postAmount = postClient.getApiResponseAsJsonArray().length();

        logger.info("Post done by user: " +postAmount + ", for userName "+userName);

        Assert.assertEquals(postAmount, expectedPostAmount, "Amount of post found is not the expected");
    }

    @Test(dataProvider = "postToUpdate", groups = {"redTest"})
    public void updatePostTest(PostRequestBody postContent, int expectedCodeResults){
        logger.info("Executing " + "updatePostTest " + "URI " + baseUri);
        postClient = new UpdatePostClient(baseUri);

        postClient.setExpectedResponseCode(expectedCodeResults);
        postClient.setPostObject(postContent);
        postClient.setPostId(postContent.getPostId());
        postClient.getApiRun();

        logger.info("Response Code " + postClient.getResponseStatusCode());
        logger.info("Client class " +postClient.toString());

        postClient = new PostByIdClient(baseUri);
        postClient.setExpectedResponseCode(200);
        postClient.setPostId(postContent.getPostId());
        postClient.getApiRun();

        logger.info("Response Code " + postClient.getResponseStatusCode());
        logger.info("Client class " +postClient.toString());

        softAssertions = new SoftAssert();

        //Following could change and be equal to object input
        softAssertions.assertEquals(postClient.getPostTitle(),postContent.getTitle(),"Post title did not get updated");
        softAssertions.assertEquals(postClient.getPostBody(),postContent.getBody(),"Post body did not get update");

        //Assumed that following cannot change. Object input has the original values
        softAssertions.assertEquals(postClient.getPostId(),postContent.getPostId(),"Post id should not change on update operation");
        softAssertions.assertEquals(postClient.getUserId(),postContent.getUserId(),"User id should not change on update operation");

        softAssertions.assertAll();
    }

    @Test(dataProvider = "postContent", groups = {"redTest"})
    public void addNewPostTest(PostRequestBody postContent, int expectedCodeResults){
        logger.info("Executing " + "addNewPostTest " + "URI " + baseUri);
        postClient = new AddNewPostClient(baseUri);

        postClient.setExpectedResponseCode(expectedCodeResults);
        postClient.setPostObject(postContent);
        postClient.getApiRun();

        logger.info("Response Code " + postClient.getResponseStatusCode());
        logger.info("Client class " +postClient.toString());

        int newPostId = postClient.getResponseStatusCode();

        postClient = new PostByIdClient(baseUri);
        postClient.setExpectedResponseCode(200);
        postClient.setPostId(newPostId);
        postClient.getApiRun();

        logger.info("Response Code " + postClient.getResponseStatusCode());
        logger.info("Client class " +postClient.toString());

        Assert.assertTrue(((PostByIdClient)postClient).checkPostUserId(postContent.getUserId()), "Post was supposed to be done but not found");

    }
}
