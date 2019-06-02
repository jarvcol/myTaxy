package test.testClasses;

import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.testData.UsersClient;
import test.utils.JsonUtilities;

public class UsersTest extends BaseTest {

    private UsersClient client;

    @Test(dataProvider = "userNames")
    public void getUserIdFromUserName(String userName, int expectedId){
        logger.info("Executing " + "getUserIdFromName " + "URI " + baseUri);
        client = new UsersClient(baseUri);

        client.setExpectedResponseCode(200);
        client.getApiRun();

        logger.info("Response Code " + client.getResponseStatusCode());
        logger.info("Client class " +client.toString());

        int userIdOnResponse = JsonUtilities.getUserIdByUserNameFromUserList(userName, client.getApiResponseAsJsonArray());

        logger.info("User Id found: " +userIdOnResponse + ", for userName "+userName);

        Assert.assertEquals(userIdOnResponse, expectedId, "Response ID is not the expected one");
    }
}
