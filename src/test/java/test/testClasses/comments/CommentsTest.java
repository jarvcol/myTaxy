package test.testClasses.comments;

import org.testng.Assert;
import org.testng.annotations.*;
import test.clients.comments.CommentsByPostIdClient;
import test.utils.EmailValidator;

import java.util.HashMap;

public class CommentsTest extends BaseCommentsTest {

    @Parameters({ "userName" })
    @BeforeClass(alwaysRun = true)
    public void setUserName(String userName){
        super.setUsername(userName);
    }

    @Test(dataProvider = "postId")
    public void getCommentsByPostId(int postId, int expectedCommentstAmount){
        logger.info("Executing " + "getCommentsByPostId " + "URI " + baseUri);
        commentsClient = new CommentsByPostIdClient(baseUri);

        commentsClient.setExpectedResponseCode(200);
        commentsClient.setPostId(postId);
        commentsClient.getApiRun();

        logger.info("Response Code " + commentsClient.getResponseStatusCode());
        logger.info("Client class " +commentsClient.toString());

        logger.info("Comments done by users: " +commentsClient.getCommentsAmount() + ", for Post Id "+postId);

        Assert.assertEquals(commentsClient.getCommentsAmount(), expectedCommentstAmount, "Amount of comments found is not the expected");
    }

    @Test(dataProvider = "commentsOnUsersPosts")
    public void checkEmailFormat(Object comment){
        logger.info("Executing " + "checkEmailFormat " + "URI " + baseUri);
        logger.info("Comment id " + ((HashMap) comment).get("id").toString());

        logger.info("Checking email format " + ((HashMap) comment).get("email"));
        Assert.assertTrue(EmailValidator.checkEmailFormat((((HashMap) comment).get("email")).toString()));
    }

}
