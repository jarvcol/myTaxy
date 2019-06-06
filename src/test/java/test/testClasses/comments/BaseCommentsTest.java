package test.testClasses.comments;

import org.testng.annotations.DataProvider;
import test.BaseTest;
import test.clients.comments.CommentsByPostIdClient;
import test.clients.posts.PostByUserClient;
import test.clients.users.UsersClient;
import test.utils.JsonUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseCommentsTest extends BaseTest {

    protected CommentsByPostIdClient commentsClient;
    protected String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @DataProvider(name="postId")
    public Object[][] postIdProvider(){
        String userName = username;

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        int userId = ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName);

        apiClient = new PostByUserClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        return new Object[][]{
                {((PostByUserClient)apiClient).getListOfPostId().get(0), 5}
        };
    }

    @DataProvider(name="commentsOnUsersPosts")
    public Iterator<Object[]> getCommentsOnUserPosts(){
        String userName = username;

        apiClient = new UsersClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        int userId = ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName);

        apiClient = new PostByUserClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        ((PostByUserClient)apiClient).setUserId(userId);
        apiClient.getApiRun();

        List<Integer> postsByUser = ((PostByUserClient)apiClient).getListOfPostId();

        apiClient = new CommentsByPostIdClient(baseUri);
        apiClient.setExpectedResponseCode(200);
        List<Object> commentsOnAllPost = new ArrayList<Object>();

        for (Integer postId: postsByUser) {
            ((CommentsByPostIdClient)apiClient).setPostId(postId);
            apiClient.getApiRun();
            commentsOnAllPost.addAll(apiClient.getApiResponseAsJsonArray().toList());
            apiClient.resetRequest();
        }

        return JsonUtilities.getIterableFromList(commentsOnAllPost).iterator();
    }

}
