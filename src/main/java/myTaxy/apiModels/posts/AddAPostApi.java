package myTaxy.apiModels.posts;

import myTaxy.apiModels.BaseApi;
import pojo.PostRequestBody;

import static io.restassured.RestAssured.given;

public class AddAPostApi extends BaseApi {

    private final String resource="/posts";
    private final String type="post";
    private PostRequestBody postRequestBody;

    public AddAPostApi(String baseUri) {
        super(baseUri);
    }

    public void setPostRequestBody(PostRequestBody postRequestBody){
        this.postRequestBody = postRequestBody;
    }

    @Override
    public void createBasicRequest() {
        super.setBaseUri(baseUri);
        super.setBasePath(resource);
        super.setRequestContentType("application/json");
        super.setPostRequestBody(postRequestBody);
        super.createRequest();
    }

    @Override
    public void executeRequest() {
        apiResponse = given().spec(requestSpecification).post();
    }

    @Override
    public void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }

    @Override
    public String toString() {
        return "AddAPostApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }

}
