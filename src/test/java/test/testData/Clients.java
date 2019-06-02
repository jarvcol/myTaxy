package test.testData;

import myTaxy.apiModels.BaseApi;
import myTaxy.apiModels.users.UsersApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class Clients {

    private BaseApi appiClient;
    private String baseUri;

    public Clients(String baseUri){
        this.baseUri = baseUri;
    }

    public void getUserApiRun(int expectedResponseCode){
        appiClient = new UsersApi(baseUri);

        appiClient.setExpectedStatusCode(expectedResponseCode);

        appiClient.perform();
    }

    public JSONObject getResponseJsonObject(){
        return appiClient.getApiResponseAsJsonObject();
    }

    public JSONArray getResponseJsonArray(){
        return appiClient.getApiResponseAsJsonArray();
    }

    public String getResponseString(){
        return appiClient.getApiResponseAsString();
    }

    public int getResponseStatusCode(){
        return appiClient.getResponseStatusCode();
    }

    @Override
    public String toString() {
        return ((UsersApi) appiClient).toString();
    }
}
