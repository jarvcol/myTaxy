package test.clients;

import myTaxy.apiModels.BaseApi;
import org.json.JSONArray;
import org.json.JSONObject;
import test.utils.JsonUtilities;

public abstract class BaseClient {

    protected BaseApi apiClient;
    protected String baseUri;

    protected int expectedResponseCode;

    public BaseClient(String baseUri){
        this.baseUri = baseUri;
    }

    public abstract void getApiRun();

    public void resetRequest(){
        apiClient.resetRequest();
    }

    public String getApiResponseAsString() {
        return apiClient.getApiResponse().asString();
    }

    public JSONObject getApiResponseAsJsonObject() {
        return new JSONObject(this.getApiResponseAsString());
    }

    public JSONArray getApiResponseAsJsonArray() {
        return new JSONArray(this.getApiResponseAsString());
    }

    public int getResponseStatusCode(){
        return apiClient.getResponseStatusCode();
    }

    public void setExpectedResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }
}
