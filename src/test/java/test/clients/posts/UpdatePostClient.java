package test.clients.posts;

import myTaxy.apiModels.posts.UpdateAPostApi;
import pojo.PostRequestBody;

public class UpdatePostClient extends BasePostClient {

    public UpdatePostClient(String baseUri){
        super(baseUri);
        apiClient = new UpdateAPostApi(baseUri);
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
