package housingfinder;

/**
 * This class keeps track of suites in listings.
 */
public class Suite {
    public Double price;
    public int numBeds;
    public int numBaths;
    public int numAvailable;

    /**
     * Constructor.
     * @param price - price of suite
     * @param numBeds - number of beds in suite
     * @param numBaths - number of baths in suite
     * @param numAvailable - number of suites available
     */
    public Suite(Double price, int numBeds, int numBaths, int numAvailable) {
        this.price = price;
        this.numBeds = numBeds;
        this.numBaths = numBaths;
        this.numAvailable = numAvailable;
    }

    /**
     * This method gets the price of the suite.
     * @return price of suite
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * This method gets the number of beds for the suite.
     * @return number of beds
     */
    public int getNumBeds() {
        return this.numBeds;
    }

    /**
     * This method gets the number of baths for the suite.
     * @return number of baths
     */
    public int getNumBaths() {
        return this.numBaths;
    }

    /**
     * This method gets the number of this suite available.
     * @return number of available suites
     */
    public int getNumAvailable() {
        return this.numAvailable;
    }

    /**
     * This method formats the suite information in a readable way.
     * @return String to be displayed
     */
    public String toString() {
        return "\nMonthly rent: $" + this.price + "\nDetails: \nNumber of bedrooms: " +
                this.numBeds + "\nNumber of bathrooms: " + this.numBaths +
                "\nNumber of units available: " + this.numAvailable + "\n";
    }
}
