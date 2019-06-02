package test.clients;

import myTaxy.apiModels.comments.CommentsByPostApi;

public class CommentsByPostIdClient extends BaseClient {

    private int postId;

    public CommentsByPostIdClient(String baseUri){
        super(baseUri);
        apiClient = new CommentsByPostApi(baseUri);
    }

    public void setPostId(int postId){
        this.postId = postId;
    }

    @Override
    public void getApiRun(){
        apiClient.setExpectedStatusCode(expectedResponseCode);
        ((CommentsByPostApi)apiClient).setPostId(postId);
        apiClient.perform();
    }

    @Override
    public String toString() {
        return "Client for API: "+((CommentsByPostApi) apiClient).toString();
    }
}
