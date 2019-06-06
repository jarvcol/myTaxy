package test.testClasses.users;

import test.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.clients.users.UsersClient;

public class UsersTest extends BaseUsersTest {

    @Test(dataProvider = "userNames")
    public void getUserIdFromUserName(String userName, int expectedId){
        logger.info("Executing " + "getUserIdFromName " + "URI " + baseUri);
        usersClient = new UsersClient(baseUri);

        usersClient.setExpectedResponseCode(200);
        usersClient.getApiRun();

        logger.info("Response Code " + usersClient.getResponseStatusCode());
        logger.info("Client class " +usersClient.toString());

        int userIdOnResponse = usersClient.getUserIdByUserNameFromUserList(userName);

        logger.info("User Id found: " +userIdOnResponse + ", for userName "+userName);

        Assert.assertEquals(userIdOnResponse, expectedId, "Response ID is not the expected one");
    }
}
