package test.testClasses;

import test.BaseTest;
import myTaxy.apiModels.users.UsersApi;
import org.testng.annotations.Test;
import org.testng.Assert;
import test.testData.Clients;
import test.utils.JsonUtilities;

public class FirstTest extends BaseTest {

    private Clients clients;

    @Test
    public void baseClientTest(){
        logger.info("Executing first test" + baseUri);
        clients = new Clients(baseUri);

        clients.getUserApiRun(200);
        logger.info("Executing first test");

        Assert.assertTrue(clients.getResponseString().length() != 0, "Users where not retrieve");
    }

    @Test()
    public void getUserIdFromUserName(){
        logger.info("Executing " + "getUserIdFromName" + "URI" + baseUri);
        clients = new Clients(baseUri);

        clients.getUserApiRun(200);

        logger.info("Response Code" + clients.getResponseStatusCode());
        logger.info("Client class " +clients.toString());

        int userIdOnResponse = JsonUtilities.getUserIdByUserNameFromUserList("Samantha", clients.getResponseJsonArray());

        logger.info("User Id found" +userIdOnResponse);

        Assert.assertEquals(3, userIdOnResponse, "Response ID is not the expected one");
    }
}
