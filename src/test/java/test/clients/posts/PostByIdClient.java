package test.clients.posts;

import myTaxy.apiModels.posts.PostByIdApi;
import test.clients.BaseClient;

public class PostByIdClient extends BaseClient {

    private int postId;

    public PostByIdClient(String baseUri){
        super(baseUri);
        apiClient = new PostByIdApi(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((PostByIdApi)apiClient).setPostId(postId);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }

    public boolean checkPostUserId(int userId){
        return userId == getApiResponseAsJsonObject().getInt("userId");
    }
}
