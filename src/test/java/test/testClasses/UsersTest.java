package test.testClasses;

import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.users.UsersClient;

public class UsersTest extends BaseTest {

    @Test(dataProvider = "userNames")
    public void getUserIdFromUserName(String userName, int expectedId){
        logger.info("Executing " + "getUserIdFromName " + "URI " + baseUri);
        apiClient = new UsersClient(baseUri);

        apiClient.setExpectedResponseCode(200);
        apiClient.getApiRun();

        logger.info("Response Code " + apiClient.getResponseStatusCode());
        logger.info("Client class " +apiClient.toString());

        int userIdOnResponse = ((UsersClient)apiClient).getUserIdByUserNameFromUserList(userName);

        logger.info("User Id found: " +userIdOnResponse + ", for userName "+userName);

        Assert.assertEquals(userIdOnResponse, expectedId, "Response ID is not the expected one");
    }
}
