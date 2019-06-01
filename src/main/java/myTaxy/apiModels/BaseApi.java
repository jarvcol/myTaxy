package myTaxy.apiModels;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;


public abstract class BaseApi {

    protected String baseUri;
    protected RequestSpecBuilder requestSpecBuilder;
    protected RequestSpecification requestSpecification;
    protected ResponseSpecBuilder responseSpecBuilder;
    protected ResponseSpecification responseSpecification;
    protected Response apiResponse;
    protected int expectedStatusCode;

    public BaseApi(String baseUri){
        this.baseUri=baseUri;
        requestSpecBuilder=new RequestSpecBuilder();
        responseSpecBuilder=new ResponseSpecBuilder();
    }

    public Response getApiResponse() {
        return apiResponse;
    }

    public String getApiResponseAsString() {
        return apiResponse.asString();
    }

    public JSONObject getApiResponseAsJsonObject() {
        return new JSONObject(apiResponse.asString());
    }

    public JSONArray getApiResponseAsJsonArray() {
        return new JSONArray(apiResponse.asString());
    }

    protected int getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    protected abstract void createRequest();
    protected abstract void executeRequest();
    protected abstract void validateResponse();

    public void perform(){
        createRequest();
        executeRequest();
        validateResponse();
    }
}
