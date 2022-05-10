package housingfinder;
import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class loads accounts and listings from json files.
 */
public class DataLoader {
    private ArrayList<Account> validList;
    private ArrayList<Listing> listings;

    /**
     * Constructor.
     */
    public DataLoader() {}

    /**
     * This method loads the accounts from json and
     * formats them into an appropriate list. It
     * should be called at the beginning of the program.
     * @return - list of accounts from json
     */
    public ArrayList<Account> fillAccounts() {
        validList = new ArrayList<Account>();
        String accountFile = "./src/Accounts.JSON";
        String email = "";
        String password = "";
        String name = "";
        ArrayList<Listing> list = new ArrayList<>();
        String type = "";
        String paymentAddress = "";
        String damageCost = "";

        try {
            FileReader reader = new FileReader(accountFile);
            JSONArray accounts = (JSONArray) new JSONParser().parse(reader);

                for (int i = 0; i < accounts.size(); i++) {
                    JSONObject a = (JSONObject) accounts.get(i);
                    email = (String) a.get("email");
                    password = (String) a.get("password");
                    name = (String) a.get("name");

                    if (a.get("list") != null) {
                        list = (ArrayList<Listing>) a.get("list");
                    }

                    if (a.get("payment address") != null) {
                        paymentAddress = a.get("payment address").toString();
                    }

                    if (a.get("damage cost") != null) {
                        damageCost = a.get("damage cost").toString();
                    }
                    type = (String) a.get("type");

                    if (type.equals("Student")) {
                        validList.add(new Student(email, password, name));
                    } else if (type.equals("Property Owner")) {
                        validList.add(new PropertyOwner(email, password, name));
                    } else if (type.equals("Real Estate Agent")) {
                        validList.add(new RealEstateAgent(email, password, name));
                    }
                }
        } catch (Exception exception) {
            System.out.println("File not found: " + exception);
        }

        return validList;
    }

    /**
     * This method loads the listings from json and
     * formats them into an appropriate list. It
     * should be called at the beginning of the program.
     * @return - list of listings from json
     */
    public ArrayList<Listing> fillListings() {
        listings = new ArrayList<Listing>();
        String listingFile = "./src/Listings.JSON";
        String title = "";
        String owner = "";
        String address = "";
        int zip = 0;
        String startDate = "";
        String endDate = "";
        String paymentAddress = "";
        String damageCost = "";
        ArrayList<String> tags = new ArrayList<String>();
        JSONArray suitesArray = new JSONArray();
        JSONObject suite = new JSONObject();

        try {
            FileReader reader = new FileReader(listingFile);
            JSONArray lists = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < lists.size(); i++) {
                JSONObject l = (JSONObject) lists.get(i);
                title = (String) l.get("title");
                owner = (String) l.get("owner");
                address = (String) l.get("address");
                zip = Integer.parseInt(l.get("zip").toString());
                startDate = (String) l.get("startDate");
                endDate = (String) l.get("endDate");
                paymentAddress = (String) l.get("paymentAddress");
                damageCost = (String) l.get("damageCost");
                suitesArray = (JSONArray) l.get("suites");
                ArrayList<Suite> suites = new ArrayList<Suite>();

                for (int j = 0; j < suitesArray.size(); j++) {
                    suite = (JSONObject) suitesArray.get(j);
                    suites.add(new Suite(Double.parseDouble(suite.get("price").toString()), Integer.parseInt(suite.get("numBeds").toString()), Integer.parseInt(suite.get("numBaths").toString()), Integer.parseInt(suite.get("numAvailable").toString())));
                }
                tags = (ArrayList<String>) l.get("tags");
                Listing newListing = new Listing(owner, title, address, zip, suites);
                newListing.setStartDate(startDate);
                newListing.setEndDate(endDate);
                newListing.setPaymentAddress(paymentAddress);
                newListing.setDamageCost(damageCost);
                listings.add(newListing);

                for (String tag : tags) {
                    newListing.addTag(tag);
                }
            }
        } catch (Exception exception) {
            System.out.println("File not found:" + exception);
        }

        return listings;
    }
}