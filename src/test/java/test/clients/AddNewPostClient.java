package test.clients;

import myTaxy.apiModels.posts.AddAPostApi;
import pojo.PostRequestBody;

public class AddNewPostClient extends BaseClient {

    private PostRequestBody postObject;

    public AddNewPostClient(String baseUri){
        super(baseUri);
        apiClient = new AddAPostApi(baseUri);
    }

    public void setPostObject(PostRequestBody postObject){
        this.postObject = postObject;
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

    public int getNewPostId(){
        return getApiResponseAsJsonObject().getInt("userId");
    }
}
