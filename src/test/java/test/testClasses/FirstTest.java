package test.testClasses;

import test.BaseTest;
import myTaxy.apiModels.users.UsersApi;
import org.testng.annotations.Test;
import org.testng.Assert;

public class FirstTest extends BaseTest {

    private UsersApi getUsersApi;

    @Test
    public void baseClientTest(){
        logger.info("Executing first test" + baseUri);
        getUsersApi = new UsersApi(baseUri);

        getUsersApi.setExpectedStatusCode(200);

        logger.info("Executing first test" + getUsersApi.toString());

        getUsersApi.perform();

        Assert.assertTrue(getUsersApi.getApiResponseAsString().length() != 0, "Users where not retrieve");
    }
}
