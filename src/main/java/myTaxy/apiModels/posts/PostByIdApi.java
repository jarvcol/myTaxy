package myTaxy.apiModels.posts;

import myTaxy.apiModels.BaseApi;

import static io.restassured.RestAssured.given;

public class PostByIdApi extends BaseApi {

    private final String resource="/posts/{postId}";
    private final String type="get";
    private int postId;

    public PostByIdApi(String baseUri) {
        super(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void createBasicRequest() {
        super.setBaseUri(baseUri);
        super.setBasePath(resource);
        super.setPathParameter("postId",postId);
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
        return "PostByIdApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }
}
