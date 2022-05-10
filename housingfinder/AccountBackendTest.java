package housingfinder;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class AccountBackendTest {

    /**@BeforeClass
    public static void oneTimeSetup() {
        AccountBackend accountBackend;
        Account account;
        accountBackend = AccountBackend.getInstance();
        account = new Account("test email", "test password", "test name");
        assertNotNull(accountBackend, "Test Failed: getting instance of AccountBackend returned null");
    }*/

    @Test
    public void testLoadJSON_ChangeOfState() {
        AccountBackend accountBackend;
        accountBackend = AccountBackend.getInstance();

        ArrayList<Account> initialList = accountBackend.getValidList();
        accountBackend.loadJSON();
        ArrayList<Account> newList = accountBackend.getValidList();
        assertNotEquals(initialList, newList, "loadJSON() did not change validList");
    }

    @Test
    public void testAddAccount_ChangeOfState() {
        AccountBackend accountBackend = AccountBackend.getInstance();

        ArrayList<Account> initialList = accountBackend.getValidList();
        accountBackend.addAccount(new Account("new email", "new pass", "new name"));
        ArrayList<Account> newList = accountBackend.getValidList();
        assertNotEquals(initialList, newList, "new account was not added to validList");
    }

    @Test
    public void testValidateAccount_True() {
        AccountBackend accountBackend;
        Account account;
        accountBackend = AccountBackend.getInstance();
        account = new Account("test email", "test password", "test name");
        accountBackend.addAccount(account);
        boolean validated = accountBackend.validateAccount(account.getEmail(), account.getPassword());
        assertTrue(validated, "Test Fail: valid account not evaluated as valid");
    }

    @Test
    public void testValidateAccount_False() {
        AccountBackend accountBackend = AccountBackend.getInstance();

        boolean validated = accountBackend.validateAccount("fake account", "fake password");
        assertFalse(validated, "Test Fail: invalid account evaluated as valid");
    }

    @Test
    public void testLogIn_Authenticated() {
        AccountBackend accountBackend = AccountBackend.getInstance();
        Account account = new Account("test email", "test password", "test name");

        Account loggedInAccount = accountBackend.logIn(account.getEmail(), account.getPassword());
        assertNotNull(loggedInAccount, "Test Fail: valid account was not logged in");
    }

    @Test
    public void testLogIn_NotAuthenticated() {
        AccountBackend accountBackend = AccountBackend.getInstance();

        Account loggedInAccount = accountBackend.logIn("fake account", "fake password");
        assertNull(loggedInAccount, "Test Fail: invalid account was logged in");
    }
}
