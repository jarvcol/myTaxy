package test.clients.posts;

import pojo.PostRequestBody;
import test.clients.BaseClient;

public abstract class BasePostClient extends BaseClient {

    protected PostRequestBody postObject;
    protected int postId;
    protected int userId;

    public BasePostClient(String baseUri) {
        super(baseUri);
    }

    public abstract void getApiRun();

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

    public void setPostObject(PostRequestBody postObject){
        this.postObject = postObject;
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }


}
