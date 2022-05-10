package housingfinder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataWriterTest {
    DataWriter write;
    DataLoader load;

    @BeforeClass
    public void oneTimeSetup() {
        write = new DataWriter();
        load = new DataLoader();
    }

    @Test
    public void testUpdateAccounts_Equals() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(new Account("new account", "pass", "acc"));
        write.updateAccounts(accounts);
        ArrayList<Account> loadedAccounts = load.fillAccounts();
        assertNotEquals(accounts, loadedAccounts, "Test Fail: written and loaded accounts do not match (writer side)");
    }

    @Test
    public void testUpdateListings_Equals() {
        ArrayList<Listing> listings = new ArrayList<Listing>();
        ArrayList<Suite> suites = new ArrayList<Suite>();
        suites.add(new Suite(600.0, 2, 2, 2));
        listings.add(new Listing("new listing owner", "new listing title",
                "new listing address", 20000, suites));
        write.updateListings(listings);
        ArrayList<Listing> loadedListings = load.fillListings();
        assertNotEquals(listings, loadedListings, "Test Fail: written and loaded listings do not match (writer side)");
    }
}
