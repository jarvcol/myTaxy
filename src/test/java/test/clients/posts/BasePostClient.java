package test.clients.posts;

import test.clients.BaseClient;

public abstract class BasePostClient extends BaseClient {

    public BasePostClient(String baseUri) {
        super(baseUri);
    }

    public abstract void getApiRun();

    public int getPostId(){
        return getApiResponseAsJsonObject().getInt("id");
    }

    public String getPostTitle(){
        return getApiResponseAsJsonObject().getString("title");
    }

    public String getPostBody(){
        return getApiResponseAsJsonObject().getString("body");
    }

    public int getUserId(){
        return getApiResponseAsJsonObject().getInt("userId");
    }

}
