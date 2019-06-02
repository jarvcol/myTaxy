package myTaxy.apiModels.posts;

import myTaxy.apiModels.BaseApi;
import static io.restassured.RestAssured.given;

public class PostByUserApi extends BaseApi {

    private final String resource="/posts?userId={userId}";
    private final String type="get";
    private int userId;

    public PostByUserApi(String baseUri) {
        super(baseUri);
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setBasePath(resource);
        requestSpecBuilder.addPathParam("userId",userId);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).get();
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }

    @Override
    public String toString() {
        return "PostByUserApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }


}
