package test.clients.posts;

import myTaxy.apiModels.posts.AddAPostApi;
import pojo.PostRequestBody;
import test.clients.BaseClient;

public class AddNewPostClient extends BasePostClient {

    public AddNewPostClient(String baseUri){
        super(baseUri);
        apiClient = new AddAPostApi(baseUri);
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((AddAPostApi)apiClient).setPostRequestBody(postObject);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }
}
