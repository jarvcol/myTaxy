package test.testClasses;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.BaseTest;
import test.clients.CommentsByPostIdClient;

public class CommentsTest extends BaseTest {

    private CommentsByPostIdClient client;

    @Parameters({ "userName" })
    public CommentsTest(String username){
        super(username);
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

}
