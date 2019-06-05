package myTaxy.apiModels.comments;

import myTaxy.apiModels.BaseApi;
import static io.restassured.RestAssured.given;

public class CommentsByPostApi extends BaseApi {

    private final String resource="/comments?postId={postId}";
    private final String type="get";
    private int postId;

    public CommentsByPostApi(String baseUri) {
        super(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void createBasicRequest() {
        super.setBaseUri(baseUri);
        super.setBasePath(resource);
        super.setQueryParamter("postId",postId);
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
        return "PostByUserApi{" +
                "type=" + type + '\'' +
                "resource='" + resource + '\'' +
                "at="+ baseUri +'\'' +
                '}';
    }

}
