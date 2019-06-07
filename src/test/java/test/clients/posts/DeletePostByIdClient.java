package test.clients.posts;

import myTaxy.apiModels.posts.DeletePostApi;
import myTaxy.apiModels.posts.UpdateAPostApi;

public class DeletePostByIdClient extends BasePostClient {

    public DeletePostByIdClient(String baseUri){
        super(baseUri);
        apiClient = new DeletePostApi(baseUri);
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((DeletePostApi)apiClient).setPostId(postId);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }
}
