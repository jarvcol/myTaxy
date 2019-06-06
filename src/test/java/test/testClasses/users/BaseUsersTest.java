package test.testClasses.users;

import org.testng.annotations.DataProvider;
import test.BaseTest;
import test.clients.users.UsersClient;

public class BaseUsersTest extends BaseTest {

    protected UsersClient usersClient;

    @DataProvider(name="userNames")
    public Object[][] userNamesProvider(){
        return new Object[][]{
                {"Bret", 1},
                {"Samantha", 3}
        };
    }
}
