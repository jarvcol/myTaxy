package test.testData;

import myTaxy.apiModels.BaseApi;
import myTaxy.apiModels.users.UsersApi;
import myTaxy.apiModels.posts.PostByUserApi;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseClient {

    protected BaseApi apiClient;
    protected String baseUri;

    protected int expectedResponseCode;

    public BaseClient(String baseUri){
        this.baseUri = baseUri;
    }

    public abstract void getApiRun();

    public String getApiResponseAsString() {
        return apiClient.getApiResponse().asString();
    }

    public JSONObject getApiResponseAsJsonObject() {
        return new JSONObject(apiClient.getApiResponse().asString());
    }

    public JSONArray getApiResponseAsJsonArray() {
        return new JSONArray(apiClient.getApiResponse().asString());
    }

    public int getResponseStatusCode(){
        return apiClient.getResponseStatusCode();
    }

    public void setExpectedResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }
}
