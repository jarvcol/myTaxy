package myTaxy.apiModels.comments;

import myTaxy.apiModels.BaseApi;
import static io.restassured.RestAssured.given;

public class CommentsByPost extends BaseApi {

    private final String resource="/comments?postId={postId}";
    private final String type="get";
    private int postId;

    public CommentsByPost(String baseUri) {
        super(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void createRequest() {
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setBasePath(resource);
        requestSpecBuilder.addQueryParam("postId",postId);
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
