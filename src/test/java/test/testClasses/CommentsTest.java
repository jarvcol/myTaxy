package test.testClasses;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;
import test.BaseTest;
import test.clients.CommentsByPostIdClient;
import test.utils.VerificationMethods;

import java.util.HashMap;

public class CommentsTest extends BaseTest {

    private CommentsByPostIdClient client;

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName) throws Exception{
        super.setUsername(userName);
    }

    @Test(dataProvider = "postId")
    public void getCommentsByPostId(int postId, int expectedCommentstAmount){
        logger.info("Executing " + "getCommentsByPostId " + "URI " + baseUri);
        client = new CommentsByPostIdClient(baseUri);

        client.setExpectedResponseCode(200);
        client.setPostId(postId);
        client.getApiRun();

        logger.info("Response Code " + client.getResponseStatusCode());
        logger.info("Client class " +client.toString());

        int commentsAmount = client.getApiResponseAsJsonArray().length();

        logger.info("Comments done by users: " +commentsAmount + ", for Post Id "+postId);

        Assert.assertEquals(commentsAmount, expectedCommentstAmount, "Amount of comments found is not the expected");
    }

    @Test(dataProvider = "commentsOnUsersPosts")
    public void checkEmailFormat(Object comment){
        logger.info("Executing " + "checkEmailFormat " + "URI " + baseUri);
        logger.info("Comment id " + ((HashMap) comment).get("id").toString());

        logger.info("Checking email format " + ((HashMap) comment).get("email"));
        Assert.assertTrue(VerificationMethods.checkEmailFormat((((HashMap) comment).get("email")).toString()));
    }

}
