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
    public void createRequest() {
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setBasePath(resource);
        requestSpecBuilder.addQueryParam("userId",userId);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    public void executeRequest() {
        apiResponse = given().spec(requestSpecification).get();
    }

    @Override
    public void validateResponse() {
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
