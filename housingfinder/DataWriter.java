package housingfinder;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;

/**
 * This class writes accounts and listings to json files.
 */
public class DataWriter {

    /**
     * Constructor.
     */
    public DataWriter() {}

    /**
     * This method updates the accounts json with the current
     * accounts stored in system memory. This method is meant to be
     * called only after accounts are loaded from json. Writing
     * to the account json will overwrite the file.
     * @param accounts - ArrayList of accounts to be updated
     */
    public void updateAccounts(ArrayList<Account> accounts) {
        JSONArray allAccounts = new JSONArray();
        String accountsFile = "./src/Accounts.JSON";

        for (int i=0; i < accounts.size(); i++) {
            Account currAccount = accounts.get(i);
            JSONObject currAccountObj = new JSONObject();
            currAccountObj.put("email", currAccount.getEmail());
            currAccountObj.put("password", currAccount.getPassword());
            currAccountObj.put("name", currAccount.getName());

            if (!currAccount.getList().isEmpty()) {
                ArrayList<Listing> list = currAccount.getList();
                JSONArray listArray = new JSONArray();

                for (Listing l : list) {
                    listArray.add(l.getTitle());
                }
                currAccountObj.put("list", listArray);
            }
            currAccountObj.put("type", currAccount.getType());
            allAccounts.add(currAccountObj);
        }

        try {
            FileWriter file = new FileWriter(accountsFile);
            file.write(allAccounts.toJSONString());
            file.flush();
        } catch(Exception exception) {
            System.out.println("File not found: " + exception);
        }
    }

    /**
     * This method updates the listings json with the current
     * listings stored in system memory. This method is meant to be
     * called only after listings are loaded from json. Writing
     * to the listings json will overwrite the file.
     * @param listings - ArrayList of listings to be updated
     */
    public void updateListings(ArrayList<Listing> listings) {
        JSONArray allListings = new JSONArray();
        String listingsFile = "./src/Listings.JSON";

        for (int i=0; i < listings.size(); i++) {
            Listing currListing = listings.get(i);
            JSONObject currListingObj = new JSONObject();
            currListingObj.put("title", currListing.getTitle());
            currListingObj.put("owner", currListing.getOwner());
            currListingObj.put("address", currListing.getAddress());
            currListingObj.put("zip", currListing.getZip());
            currListingObj.put("startDate", currListing.getStartDate());
            currListingObj.put("endDate", currListing.getEndDate());
            currListingObj.put("paymentAddress", currListing.getPaymentAddress());
            currListingObj.put("damageCost", currListing.getDamageCost());
            ArrayList<Suite> suites = currListing.getSuites();
            JSONArray suitesArray = new JSONArray();

            for (Suite suite : suites) {
                JSONObject suitesObj = new JSONObject();
                suitesObj.put("price", suite.getPrice());
                suitesObj.put("numBeds", suite.getNumBeds());
                suitesObj.put("numBaths", suite.getNumBaths());
                suitesObj.put("numAvailable", suite.getNumAvailable());
                suitesArray.add(suitesObj);
            }
            currListingObj.put("suites", suitesArray);
            ArrayList<String> tags = currListing.getTags();
            JSONArray tagsArray = new JSONArray();

            for (String tag : tags) {
                tagsArray.add(tag);
            }
            currListingObj.put("tags", tagsArray);
            allListings.add(currListingObj);
        }

        try {
            FileWriter file = new FileWriter(listingsFile);
            file.write(allListings.toJSONString());
            file.flush();
        } catch(Exception exception) {
            System.out.println("File not found: " + exception);
        }
    }
}