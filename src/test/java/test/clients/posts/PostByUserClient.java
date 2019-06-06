package test.clients.posts;

import myTaxy.apiModels.posts.PostByUserApi;
import test.clients.BaseClient;
import test.utils.JsonUtilities;

import java.util.List;

public class PostByUserClient extends BaseClient {

    private int userId;

    public PostByUserClient(String baseUri){
        super(baseUri);
        apiClient = new PostByUserApi(baseUri);
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((PostByUserApi)apiClient).setUserId(userId);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+apiClient.toString();
    }

    public List<Integer> getListOfPostId(){
        return JsonUtilities.getListOfIntegerValuesFromList("id", this.getApiResponseAsJsonArray());
    }
}
