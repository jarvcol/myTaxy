package myTaxy.apiModels.users;

import myTaxy.apiModels.BaseApi;
import static io.restassured.RestAssured.given;

public class UsersApi extends  BaseApi{

    private final String resource="/users";
    private final String type="get";

    public UsersApi(String baseUri) {
        super(baseUri);
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setBasePath(resource);
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
        return "UsersApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }
}
