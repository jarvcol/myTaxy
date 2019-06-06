package test.clients;

import myTaxy.apiModels.posts.UpdateAPostApi;
import pojo.PostRequestBody;

public class UpdatePostClient extends BaseClient {

    private PostRequestBody postObject;

    public UpdatePostClient(String baseUri){
        super(baseUri);
        apiClient = new UpdateAPostApi(baseUri);
    }

    public void setPostObject(PostRequestBody postObject){
        this.postObject = postObject;
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((UpdateAPostApi)apiClient).setPostRequestBody(postObject);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }
}
