package myTaxy.apiModels.posts;

import myTaxy.apiModels.BaseApi;

import static io.restassured.RestAssured.given;

public class DeletePostApi extends BaseApi {

    private final String resource="/posts/{postId}";
    private final String type="delete";
    private int postId;

    public DeletePostApi(String baseUri) {
        super(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void createBasicRequest() {
        super.setBaseUri(baseUri);
        super.setBasePath(resource);
        super.setPathParameter("postId", postId);
        super.createRequest();
    }

    @Override
    public void executeRequest() {
        apiResponse = given().spec(requestSpecification).delete();
    }

    @Override
    public void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }

    @Override
    public String toString() {
        return "DeletePostApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }

}
