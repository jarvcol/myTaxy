package test.clients;

import myTaxy.apiModels.posts.PostByIdApi;
import myTaxy.apiModels.posts.PostByUserApi;
import test.utils.JsonUtilities;

import java.util.List;

public class PostByIdClient extends BaseClient{

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
        return "Client for API: "+((PostByIdApi) apiClient).toString();
    }

    public boolean checkPostUserId(int userId){
        return userId == getApiResponseAsJsonObject().getInt("userId");
    }

}
