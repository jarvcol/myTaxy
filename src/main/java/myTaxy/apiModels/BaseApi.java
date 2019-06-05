package myTaxy.apiModels;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPostRequestBody;

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

    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public void resetRequest(){
        requestSpecBuilder = new RequestSpecBuilder();
    };

    public void setBaseUri(String baseUri) {
        this.requestSpecBuilder.setBaseUri(baseUri);
    }

    public void setBasePath(String resource){
        this.requestSpecBuilder.setBasePath(resource);
    }

    public void setQueryParamter(String parameterName, Object parameterValue){
        this.requestSpecBuilder.addQueryParam(parameterName, parameterValue);
    }

    public void setPostRequestBody(Object postRequestBody){
        requestSpecBuilder.setBody(postRequestBody);
    }

    public void setRequestContentType(String type){
        requestSpecBuilder.setContentType(type);
    }

    public void setPathParameter(String parameterName, Object parameterValue){
        requestSpecBuilder.addPathParam(parameterName, parameterValue);
    }

    public void createRequest(){
        requestSpecification=requestSpecBuilder.build();
    };

    protected abstract void createBasicRequest();
    protected abstract void executeRequest();
    protected abstract void validateResponse();

    public void perform(){
        createBasicRequest();
        executeRequest();
        validateResponse();
    }

    public Response getApiResponse() {
        return apiResponse;
    }

    public int getResponseStatusCode() {
        return apiResponse.getStatusCode();
    }
}
