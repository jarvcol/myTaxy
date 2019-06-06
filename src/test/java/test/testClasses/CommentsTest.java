package test.testClasses;

import org.testng.Assert;
import org.testng.annotations.*;
import test.BaseTest;
import test.clients.CommentsByPostIdClient;
import test.utils.VerificationMethods;

import java.util.HashMap;

public class CommentsTest extends BaseTest {

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName){
        super.setUsername(userName);
    }

    @Test(dataProvider = "postId")
    public void getCommentsByPostId(int postId, int expectedCommentstAmount){
        logger.info("Executing " + "getCommentsByPostId " + "URI " + baseUri);
        apiClient = new CommentsByPostIdClient(baseUri);

        apiClient.setExpectedResponseCode(200);
        ((CommentsByPostIdClient)apiClient).setPostId(postId);
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        int commentsAmount = apiClient.getApiResponseAsJsonArray().length();

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
