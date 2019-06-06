package test.clients;

import myTaxy.apiModels.users.UsersApi;
import test.utils.JsonUtilities;

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
        return "Client for API: "+apiClient.toString();
    }

    public int getUserIdByUserNameFromUserList(String userName){
        return JsonUtilities.getObjectFromListByParameter("username", userName, this.getApiResponseAsJsonArray()).getInt("id");
    }
}
