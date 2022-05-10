package housingfinder;
import java.util.ArrayList;

/**
 * This class represents all of the listings together, they are all stored in the ArrayList<Listing>
 * list.
 */
public class Listings {
    private static ArrayList<Listing> list;
    private DataLoader load;
    private DataWriter write;
    public static Listings listings;

    /**
     * Only one instance can be made.
     */
    Listings() {}

    /**
     * Method that is used to get the instance of Listings.
     * @return listings instance
     */
    public static Listings getInstance() {
        if (listings == null) {
            listings = new Listings();
        }

        return listings;
    }

    /**
     * This method loads the json into the list
     * by calling the dataloader.
     */
    public void loadJSON() {
        if (list == null) {
            list = new ArrayList<Listing>();
        }

        if (load == null) {
            load = new DataLoader();
        }
        list = load.fillListings();
    }

    /**
     * This method saves the list to json
     * by calling the datawriter.
     */
    public void updateJSON() {
        if (list != null) {

            if (write == null) {
                write = new DataWriter();
            }
            write.updateListings(list);
        } else {
            System.out.println("No accounts to save");
        }
    }

    /**
     * adds listing to list
     * @param listing - listing that is being added
     */
    public void addListing(Listing listing) {
        if (list == null) {
            list = new ArrayList<Listing>();
        }
        list.add(listing);
        updateJSON();
    }

    /**
     * deletes listing from list
     * @param listing - listing that is being deleted
     */
    public void deleteListing(Listing listing) {
        list.remove(listing);
    }

    /**
     * This method searches for matching listings
     * with tags, number of bedrooms, and number of bathrooms.
     * @param tags - tags being searched for by user
     * @param numBeds - number of beds being searched for
     * @param numBaths - number of baths being searched for
     * @return - ArrayList<Listing> holding search matches
     */
    public ArrayList<Listing> searchListings(ArrayList<String> tags, int numBeds, int numBaths) {
        ArrayList<Listing> searchMatch = new ArrayList<Listing>();
        ArrayList<String> currTags = new ArrayList<String>();
        ArrayList<Suite> currSuites = new ArrayList<Suite>();
        ArrayList<Listing> matchingListings = new ArrayList<Listing>();


        if (this.list == null) {
            this.list = new ArrayList<Listing>();
            loadJSON();
        }

        for (Listing l : this.list) {
            currTags = l.getTags();

            if (currTags != null) {

                if (currTags.containsAll(tags)) {
                    matchingListings.add(l);
                }
            }
        }

        for (Listing l : matchingListings) {
            currSuites = l.getSuites();

            if (numBeds == 0 && numBaths == 0) {
                searchMatch.add(l);
            } else if (numBeds == 0) {

                for (Suite suite : currSuites) {

                    if (suite.getNumBaths() == numBaths) {
                        searchMatch.add(l);
                    }
                }
            } else if (numBaths == 0) {

                for (Suite suite : currSuites) {

                    if (suite.getNumBeds() == numBeds) {
                        searchMatch.add(l);
                    }
                }
            } else {
                for (Suite suite : currSuites) {

                    if (suite.getNumBeds() == numBeds && suite.getNumBaths() == numBaths) {
                        searchMatch.add(l);
                    }
                }
            }
        }

        return searchMatch;
    }

    /**
     * This method gets the listings associate with an account.
     * @param account - account to get listings for
     * @return list of listings
     */
    public ArrayList<Listing> viewListings(Account account) {
        ArrayList<Listing> accountList = account.getList();

        return accountList;
    }
}
