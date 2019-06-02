package test.clients;

import myTaxy.apiModels.posts.PostByUserApi;

public class PostByUserClient extends BaseClient{

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
        return "Client for API: "+((PostByUserApi) apiClient).toString();
    }
}
