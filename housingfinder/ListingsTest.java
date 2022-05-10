package housingfinder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ListingsTest {

  @Test
  public void testloadJSON() {
    Account account = new Account("new email", "new pass", "new name");
    Listings listings;
    listings = Listings.getInstance();
    ArrayList<Listing> initialList = account.getList();
    listings.loadJSON();
    ArrayList<Listing> newList = account.getList();
    assertNotEquals(initialList, newList, "loadJSON() did not change list");
  }

  @Test
  public void testAddListing() {
    Account account = new Account("new email", "new pass", "new name");
    ArrayList<Suite> suites = null;
    Listing listing = new Listing("owner", "title", "address", 29902, suites);
    Listings listings;
    listings = Listings.getInstance();
    ArrayList<Listing> initialList = account.getList();
    listings.addListing(listing);
    ArrayList<Listing> newList = account.getList();
    assertNotEquals(initialList, newList, "Listing was not added to the list");
  }

  public void testDeleteListing() {

  }

  public void testSearchListings() {

  }

  public void testViewListings() {

  }

}
