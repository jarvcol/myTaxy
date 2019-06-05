package test.clients;

import myTaxy.apiModels.posts.AddAPostApi;
import pojo.AddPostRequestBody;
import test.utils.JsonUtilities;

import java.util.List;

public class AddNewPostClient extends BaseClient {

    private AddPostRequestBody postObject;

    public AddNewPostClient(String baseUri){
        super(baseUri);
        apiClient = new AddAPostApi(baseUri);
    }

    public void setPostObject(AddPostRequestBody postObject){
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
        return "Client for API: "+((AddAPostApi) apiClient).toString();
    }

    public int getNewPostId(){
        return getApiResponseAsJsonObject().getInt("userId");
    }
}
