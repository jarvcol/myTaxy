package test.testClasses;

import test.BaseTest;
import myTaxy.apiModels.users.UsersApi;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.utils.JsonUtilities;

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

    @Test()
    public void getUserIdFromUserName(){
        logger.info("Executing " + "getUserIdFromName" + "URI" + baseUri);
        getUsersApi = new UsersApi(baseUri);

        getUsersApi.setExpectedStatusCode(200);
        getUsersApi.perform();

        logger.info("Response Code" + getUsersApi.getResponseStatusCode());

        int userIdOnResponse = JsonUtilities.getUserIdByUserNameFromUserList("Samantha", getUsersApi.getApiResponseAsJsonArray());

        logger.info("User Id found" +userIdOnResponse);

        Assert.assertEquals(3, userIdOnResponse, "Response ID is not the expected one");
    }
}
