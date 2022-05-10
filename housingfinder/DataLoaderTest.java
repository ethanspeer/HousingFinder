package housingfinder;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
    DataLoader load;

    @BeforeClass
    public void oneTimeSetup() {
        load = new DataLoader();
    }

    @Test
    public void testFillAccounts_NotNull() {
        ArrayList<Account> accounts = load.fillAccounts();

        if (accounts.isEmpty()) {
            accounts = null;
        }
        assertNotNull(accounts, "Test Fail: no accounts were added by data loader");
    }

    @Test
    public void testFillListings_NotNull() {
        ArrayList<Listing> listings = load.fillListings();

        if (listings.isEmpty()) {
            listings = null;
        }
        assertNotNull(listings, "Test Fail: no listings were added by data loader");
    }
}
