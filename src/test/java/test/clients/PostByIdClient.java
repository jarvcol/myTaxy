package test.clients;

import myTaxy.apiModels.posts.PostByIdApi;

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
        return "Client for API: "+apiClient.toString();
    }

    public boolean checkPostUserId(int userId){
        return userId == getApiResponseAsJsonObject().getInt("userId");
    }

    public int getPostId(){
        return getApiResponseAsJsonObject().getInt("id");
    }

    public String getPostTitle(){
        return getApiResponseAsJsonObject().getString("title");
    }

    public String getPostBody(){
        return getApiResponseAsJsonObject().getString("body");
    }

    public int getUserId(){
        return getApiResponseAsJsonObject().getInt("userId");
    }
}
