package housingfinder;
import java.util.ArrayList;

/**
 * This class represents a Listing that will be added to the system
 * and saved to a json file.
 */
public class Listing {
    public String owner;
    public String title;
    public String address;
    public int zip;
    public ArrayList<Suite> suites;
    public ArrayList<String> tags;
    public static ArrayList<String> ratings;
    public String startDate;
    public String endDate;
    protected String paymentAddress;
    protected String damageCost;

    /**
     * Constructor.
     * @param owner - owner of property
     * @param title - title of property
     * @param address - address of property
     * @param zip - zipcode of property
     * @param suites - list of suites for property
     */
    public Listing(String owner, String title, String address, int zip, ArrayList<Suite> suites) {
        this.owner = owner;
        this.title = title;
        this.address = address;
        this.zip = zip;
        this.suites = suites;
    }

    /**
     * This method gets the owner of the listing.
     * @return the owner of the listing
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * This method gets the title of the listing.
     * @return the title of the listing
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method gets the address of the listing.
     * @return the address of the listing
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method returns the zipcode of the listing.
     * @return the zipcode of the listing
     */
    public int getZip() {
        return this.zip;
    }

    /**
     * This method returns the suites of the listing.
     * @return list of suites of the listing
     */
    public ArrayList<Suite> getSuites() {
        return this.suites;
    }

    /**
     * This method gets the tags of the listing.
     * @return list of tags of the listing
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * This method gets the ratings of the listing.
     * @return list of ratings of the listing
     */
    public ArrayList<String> getRatings() {
        return ratings;
    }

    /**
     * This method gets the start date of the listing.
     * @return start date of the listing
     */
    public String getStartDate() {
        if (this.startDate == null) {
            return "NONE PROVIDED";
        }

        return this.startDate;
    }

    /**
     * This method gets the end date of the listing.
     * @return end date of the listing
     */
    public String getEndDate() {
        if (this.endDate == null) {
            return "NONE PROVIDED";
        }

        return this.endDate;
    }

    /**
     * This method gets the payment address of the listing.
     * @return payment address of the listing
     */
    public String getPaymentAddress() {
        if (this.paymentAddress == null) {
            return "NONE PROVIDED";
        }

        return this.paymentAddress;
    }

    /**
     * This method gets the damage cost of the listing.
     * @return damage cost of the listing
     */
    public String getDamageCost() {
        if (this.damageCost == null) {
            return "NONE PROVIDED";
        }

        return this.damageCost;
    }

    /**
     * This method adds a tag to the list of tags.
     * @param tag - to be added to the tags
     */
    public void addTag(String tag) {

        if (this.tags == null) {
            this.tags = new ArrayList<String>();
        }
        this.tags.add(tag);
    }

    /**
     * This method adds a rating to the listing's
     * list of ratings
     * @param rate - rating to add
     */
    public void addRating(String rate) {

        if (this.ratings == null) {
            this.ratings = new ArrayList<String>();
        }
        ratings.add(rate);
    }

    /**
     * This method sets the start date for the listing.
     * @param startDate - start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * This method sets the end date for the listing.
     * @param endDate - end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * This method sets the payment address for the listing.
     * @param paymentAddress - payment address
     */
    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    /**
     * This method sets the cost of damage for the listing.
     * @param damageCost - damage cost
     */
    public void setDamageCost(String damageCost) {
        this.damageCost = damageCost;
    }

    /**
     * This method returns a formatted string with all of
     * the listing's information
     * @return formatted String
     */
    public String toString() {
        String titleStr = this.title.toUpperCase() + "\n-------------------\n";
        String ownerStr = "Property Owner: " + this.owner;
        String addressStr = "\nAddress:\n" + this.address + "\n" + this.zip;
        String display = titleStr + ownerStr + addressStr + "\nSuites:\n";

        for (Suite suite : this.suites) {
            display = display + suite.toString();
        }

        if (this.tags != null) {
            String tags = "\nAmenities:\n";

            for (String t : this.tags) {
                tags = tags + t + "\t";
            }
            display = display + tags;
        }

        return display;
    }

    /**
     * This method returns a formatted string with all of
     * the listing's information
     * @param numBeds - number of beds to show suites
     * @param numBaths - number of baths to show suites
     * @return formatted String
     */
    public String toStringSpecificSuite(int numBeds, int numBaths) {
        String titleStr = this.title.toUpperCase() + "\n-------------------\n";
        String ownerStr = "Property Owner: " + this.owner;
        String addressStr = "\nAddress:\n" + this.address + "\n" + this.zip;
        String display = titleStr + ownerStr + addressStr;

        for (Suite suite : this.suites) {

            if (numBeds == 0 && numBaths == 0) {
                display = display + suite.toString();
            } else if (numBeds == 0) {
                if (suite.getNumBaths() == numBaths) {
                    display = display + suite.toString();
                }
            } else if (numBaths == 0) {
                if (suite.getNumBeds() == numBeds) {
                    display = display + suite.toString();
                }
            } else if (suite.getNumBeds() == numBeds && suite.getNumBaths() == numBaths) {
                display = display + suite.toString();
            }
        }

        if (this.tags != null) {
            String tags = "\nAmenities:\n";

            for (String t : this.tags) {
                tags = tags + t + "\t";
            }
            display = display + tags;
        }

        return display;
    }
}
