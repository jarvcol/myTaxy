package test.testData;

import myTaxy.apiModels.users.UsersApi;

public class UsersClient extends BaseClient{

    public UsersClient(String baseUri){
        super(baseUri);
        apiClient = new UsersApi(baseUri);
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+((UsersApi) apiClient).toString();
    }
}
