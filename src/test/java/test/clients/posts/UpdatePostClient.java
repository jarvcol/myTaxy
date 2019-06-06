package test.clients.posts;

import myTaxy.apiModels.posts.UpdateAPostApi;
import pojo.PostRequestBody;
import test.clients.BaseClient;

public class UpdatePostClient extends BaseClient {

    private PostRequestBody postObject;
    private int postId;

    public UpdatePostClient(String baseUri){
        super(baseUri);
        apiClient = new UpdateAPostApi(baseUri);
    }

    public void setPostObject(PostRequestBody postObject){
        this.postObject = postObject;
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((UpdateAPostApi)apiClient).setPostRequestBody(postObject);
        ((UpdateAPostApi)apiClient).setPostId(postId);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }
}
