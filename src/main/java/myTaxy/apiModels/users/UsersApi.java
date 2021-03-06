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
    public void createBasicRequest() {
        super.setBaseUri(baseUri);
        super.setBasePath(resource);
        super.createRequest();
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
        return "UsersApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }
}
