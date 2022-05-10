package housingfinder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ListingTest{
    @BeforeClass
    public static void setUp(){
        Listings listings;
        Listing listing;
        ArrayList<Suite> suites = new ArrayList<Suite>();
        ArrayList<String> tags = new ArrayList<String>();
        listings = Listings.getInstance();
        listing = new Listing("Test owner", "Test title", "Test address", 123, suites);
        assertNotNull(listings, "Test Failed: getting instance of Listings returned null");
    }

    @Test
    public static void testAddTag(){
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Test tag");
        Listing.addTag("Test tag");
        System.out.println(tags);
        assertNotEquals(Listing.tags, tags, "Tag was not added to tags.");
    }

    @Test
    public void testAddRating(){
        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add("Test rating");
        Listing.addRating("Test rating");
        System.out.println(Listing.ratings);
        assertNotEquals(Listing.ratings, ratings, "Rating was not added to ratings.");
    }

}
