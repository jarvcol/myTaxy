package pojo;

public class PostRequestBody {

    private String title, body;
    private int userId, postId;

    public PostRequestBody(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public PostRequestBody(String title, String body, int userId, int postId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
