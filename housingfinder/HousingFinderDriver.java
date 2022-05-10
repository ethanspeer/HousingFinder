package housingfinder;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;

/**
 * This class runs the application and
 * keeps the user in the UI loop.
 */
public class HousingFinderDriver {
    public AccountBackend accounts;
    public Listings listings;
    public Scanner scanner = new Scanner(System.in);
    private static final String WELCOME_MSG = "Welcome to the Housing Finder!";
    public boolean running = true;
    public boolean loggedIn = false;
    private String[] menuOptions = {"Login", "Create Account", "Search", "Close"};
    private String[] studentOptions = {"Search", "Rate a Listing", "Get Lease Agreement", "Logout"};
    private String[] pmOptions = {"Search", "View Listings", "Add Listing", "Get Lease Agreement", "Logout"};
    private String[] reaOptions = {"Search", "View Listings", "Get Lease Agreement", "Logout"};

    /**
     * This method runs the main menu.
     * The user will always come back here
     * until they close the application.
     */
    public void run() {
        System.out.println(WELCOME_MSG);
        accounts = AccountBackend.getInstance();
        accounts.loadJSON();
        listings = Listings.getInstance();
        listings.loadJSON();

        while (running) {
            displayMainMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    login();
                    loggedIn = true;
                    break;

                case (2):
                    createAccount();
                    loggedIn = true;
                    break;

                case (3):
                    Listing listing = search();

                    if (listing != null) {
                        System.out.println("Here is the listing you chose:\n" + listing.toString());
                        System.out.println("This is as far as you can go without being logged in.");
                    }
                    break;

                case (4):
                    running = false;
                    System.out.println("Goodbye, and have a nice day.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("You must enter a proper option, try again:");
          }
        }
  }

    /**
     * This method displays the main menu.
     */
    private void displayMainMenu() {
        System.out.println("\nMain Menu:");

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
        System.out.println("\n");
  }

    /**
     * This method displays the student menu.
     */
    private void displayStudentMenu() {
        System.out.println("\nStudent Menu:");

        for (int i = 0; i < studentOptions.length; i++) {
            System.out.println((i + 1) + ". " + studentOptions[i]);
        }
        System.out.println("\n");
  }

    /**
     * This method displays the property owner menu.
     */
    private void displayPMMenu() {
        System.out.println("\nProperty Manager Menu:");

        for (int i = 0; i < pmOptions.length; i++) {
            System.out.println((i + 1) + ". " + pmOptions[i]);
        }
        System.out.println("\n");
  }

    /**
     * This method displays the real estate agent menu.
     */
    private void displayREAMenu() {
        System.out.println("\nReal Estate Agent Menu:");

        for (int i = 0; i < reaOptions.length; i++) {
            System.out.println((i + 1) + ". " + reaOptions[i]);
        }
        System.out.println("\n");
  }

    /**
     * This method logs the user in.
     */
    private void login() {
        boolean loop = true;

        while (loop) {
            System.out.println("LOGGING IN" + "\n----------------\n" + "enter 'EXIT' to go back to main menu");
            System.out.println("\nEnter email: ");
            String email = scanner.next();

            if (email.equalsIgnoreCase("EXIT")) {
                break;
            }
            System.out.println("Enter password: ");
            String pass = scanner.next();

            if (accounts.validateAccount(email, pass)) {
                Account currentAccount = accounts.logIn(email, pass);
                System.out.println("Logged in!");
                loggedIn = true;
                String type = currentAccount.getType();

                if (type.equals("Student")) {
                    studentAccount(currentAccount);
                }
                if (type.equals("Property Owner")) {
                    pmAccount(currentAccount);
                }
                if (type.equals("Real Estate Agent")) {
                    reaAccount(currentAccount);
                }
            } else {
                System.out.println("Incorrect login info. Try again, or enter 'EXIT' to go back to the main menu:\n");
            }
        }
    }

    /**
     * This method allows the user to create an account.
     */
    private void createAccount() {
        boolean loop = true;

        while (loop) {
            scanner.nextLine();
            System.out.println("Please enter if you are a student, real estate agent, or property owner.");
            String type = scanner.nextLine().toLowerCase();
            System.out.println("Enter your full name:");
            String name = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String pass = scanner.nextLine();

            if (type.equals("student")) {
                Account newAccount = new Student(email, pass, name);
                accounts.addAccount(newAccount);
                loggedIn = true;
                System.out.println("Logged in as student: " + newAccount.getEmail());
                studentAccount(newAccount);
            }
            if (type.equals("property owner")) {
                Account newAccount = new PropertyOwner(email, pass, name);
                accounts.addAccount(newAccount);
                loggedIn = true;
                System.out.println("Logged in as property owner: " + newAccount.getEmail());
                pmAccount(newAccount);
            }
            if (type.equals("real estate agent")) {
                Account newAccount = new RealEstateAgent(email, pass, name);
                accounts.addAccount(newAccount);
                loggedIn = true;
                System.out.println("Logged in as real estate agent: " + newAccount.getEmail());
                reaAccount(newAccount);
            }
            break;
        }
  }

    /**
     * This method allows the user to search for
     * a listing by specifying the number of bedrooms
     * and bathrooms and entering search tags.
     * @return Listing that the user chooses after searching.
     */
    private Listing search() {
        boolean addTag = true;
        ArrayList<String> tags = new ArrayList<String>();
        System.out.println("SEARCHING LISTINGS" + "\n--------------------\n");
        System.out.println("How many bedrooms? (Enter 0 for any)");
        int numBedrooms = scanner.nextInt();
        System.out.println("How many baths? (Enter 0 for any)");
        int numBaths = scanner.nextInt();

        while (addTag) {
            System.out.println("Possible tags:" + "\n\'pool\', " + "\'gym\', " + "\n\'close\', " +
                    "\'pets\', " + "\n\'wifi\', " + "\'furnished\', " + "\'apartment\', " + "\'washer/dryer\'");

            System.out.println("Enter tags one at a time, or 0 to finish adding tags.");
            String tag = scanner.next();

            if (tag.equals("0")) {
                addTag = false;
                break;
            }
            tags.add(tag);
            System.out.println(tag + " added!");
        }

        ArrayList<Listing> searchResults = listings.searchListings(tags, numBedrooms, numBaths);

        if (searchResults.isEmpty()) {
            System.out.println("No listings found for those search parameters.");
        } else {
            boolean loop = true;

            while (loop) {
                System.out.println("Choose a listing to look at in more detail or 0 to go back.");
                Listing chosenListing;
                int iter = 1;

                for (int i = 1; i < searchResults.size(); i++) {
                    if (searchResults.get(i-1).equals(searchResults.get(i))) {
                    searchResults.remove(i);
                    }
                }

                for (Listing l : searchResults) {
                    System.out.println("\n" + iter + ". " + l.toStringSpecificSuite(numBedrooms, numBaths));
                    iter++;
                }

                int command = scanner.nextInt();

                if (command == 0) {
                    return null;
                } else {
                    chosenListing = searchResults.get(command - 1);
                }

                if (chosenListing != null) {
                    return chosenListing;
                }

            }
        }
        return null;
    }

    /**
     * This method lets a user chose a suite for leasing.
     * @param listing - the listing to look at suites for
     * @return the suite the user chose
     */
    private Suite chooseSuite(Listing listing) {
        boolean suiteLoop = true;
        Suite chosenSuite = null;

        while (suiteLoop) {
            System.out.println("Choose your suite:");
            int iter = 1;

            for (Suite suite : listing.getSuites()) {
                System.out.println("\n" + iter + ". " + suite.toString());
                iter++;
            }
            int suiteCommand = scanner.nextInt();
            chosenSuite = listing.getSuites().get(suiteCommand - 1);

            if (chosenSuite != null) {
                suiteLoop = false;
            }
        }

        return chosenSuite;
    }

    /**
     * This method runs the student account menu.
     * @param account - account of the logged in student
     */
    private void studentAccount(Account account) {
        boolean loop = true;
        Listing chosenListing = null;

        while(loop) {
            displayStudentMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    chosenListing = search();
                    break;

                case (2):
                    if (chosenListing != null) {
                        rate(chosenListing);
                    }

                    while (chosenListing == null) {
                        System.out.println("Search for a listing to rate...");
                        chosenListing = search();

                        if (chosenListing == null) {
                            System.out.println("Continue searching? (Y/N)");

                            if (!scanner.next().toUpperCase().equals("Y")) {
                                break;
                            }
                        } else {
                            rate(chosenListing);
                        }
                    }
                    break;

                case (3):
                    if (chosenListing != null) {
                        Suite chosenSuite = chooseSuite(chosenListing);
                        lease(chosenListing, chosenSuite, account);
                    }

                    while (chosenListing == null) {
                        Suite chosenSuite = new Suite(null, 0, 0, 0);
                        System.out.println("Search for a listing to lease...");
                        chosenListing = search();

                        if (chosenListing == null) {
                            System.out.println("Continue searching? (Y/N)");

                            if (!scanner.next().toUpperCase().equals("Y")) {
                                break;
                            }
                        } else {
                            chosenSuite = chooseSuite(chosenListing);
                            lease(chosenListing, chosenSuite, account);
                        }
                    }
                    break;

                case (4):
                    System.out.println("logging out " + account.getEmail() + "\n");
                    loop = false;
            }
        }
    }

    /**
     * This method runs the property owner account menu.
     * @param account - account of the logged in property owner
     */
    private void pmAccount(Account account) {
        boolean loop = true;

        while (loop) {
            displayPMMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    search();
                    break;

                case (2):
                    view(account);
                    break;

                case (3):
                    Listing listing = addListing(account);
                    System.out.println("New listing added: \n" + listing.toString());
                    break;

                case (4):
                    break;

                case (5):
                    System.out.println("logging out " + account.getEmail());
                    loop = false;
            }
        }
    }

    /**
     * This method runs the real estate agent account menu.
     * @param account - account of the logged in real estate agent
     */
    private void reaAccount(Account account) {
        boolean loop = true;

        while (loop) {
            displayREAMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    search();
                    break;

                case (2):
                    view(account);
                    break;

                case (3):
                    break;

                case (4):
                    System.out.println("logging out " + account.getEmail());
                    loop = false;
            }
        }
    }

    /**
     * This method returns the variable that should
     * go in the spot in the leasing agreement.
     * @param variable - variable from leasing agreement template
     * @param listing - listing to get variable from
     * @param suite - suite to get variable from
     * @param account - account to get variable from
     * @return String holding variable to go in leasing agreement
     */
    private String getVariable(String variable, Listing listing, Suite suite, Account account) {
        String ownerName = listing.getOwner();

        if (variable.equals("DATE")) {
            return LocalDate.now().toString();
        }
        else if (variable.equals("LANDLOARD")) {
            return listing.getOwner();
        }
        else if (variable.equals("TENANT(s)")) {
            return account.getName();
        }
        else if (variable.equals("NUM_BED")) {
            return Integer.toString(suite.getNumBeds());
        }
        else if (variable.equals("NUM_BATH")) {
            return Integer.toString(suite.getNumBaths());
        }
        else if (variable.equals("PROPERTY_ADDRESS")) {
            return listing.getAddress();
        }
        else if (variable.equals("ZIP")) {
            return Integer.toString(listing.getZip());
        }
        else if (variable.equals("START DATE")) {
            return listing.getStartDate();
        }
        else if (variable.equals("END DATE")) {
            return listing.getEndDate();
        }
        else if (variable.equals("RENT")) {
            return Double.toString(suite.getPrice());
        }
        else if (variable.equals("PAYMENT ADDRESS")) {
            return listing.getPaymentAddress();
        }
        else if (variable.equals("DAMAGE COST")) {
            return listing.getDamageCost();
        }
        else if (variable.equals("TENANT 1")) {
            return account.getName();
        }
        else {
            return "UNKNOWN";
        }
    }

    /**
     * This method parses the leasing agreement at "<" and ">".
     * @param currLine - line to parse
     * @param listing - listing to send in getVariable
     * @param suite - suite to send in getVariable
     * @param account - account to send in getVariable
     * @return String holding new parsed line
     */
    private String parseLine(String currLine, Listing listing, Suite suite, Account account) {
        String lines[] = currLine.split("<");
        String parsedLine = "";

        for (String line : lines) {

            if (line.contains(">")) {
                String split[] = line.split(">");
                String variable = split[0];
                variable = getVariable(variable, listing, suite, account);
                parsedLine = parsedLine + variable;

                if (split.length > 1) {
                    String remainder = split[1];
                    parsedLine = parsedLine + remainder;
                }
            } else {
                parsedLine = parsedLine + line;
            }
        }

        return parsedLine;
    }

    /**
     * This method creates a leasing agreement between a student and a property owner
     * @param listing - listing agreement is being signed on
     * @param suite - suite agreement is being signed on
     * @param account - account that is signing the lease
     */
    private void lease(Listing listing, Suite suite, Account account) {
        String agreementTemplate = ("./src/Template Lease Agreement.txt");
        String saveFile = ("./src/Lease Agreement.txt");
        scanner.nextLine();
        boolean findRoommate = true;
        Account roommate = null;
        System.out.println("Would you like to room with another user? (Y/N)");

        while (findRoommate) {

            if (scanner.nextLine().toUpperCase().equals("Y")) {
                System.out.println("Enter the email of the person you want to room with:");
                String email = scanner.nextLine();

            for (Account currAccount : accounts.getValidList()) {

                if (currAccount.getEmail().equals(email)) {
                    roommate = accounts.getAccount(currAccount.getEmail());
                    System.out.println("You chose: " + roommate.getName());
                    findRoommate = false;
                    break;
                }
            }

            if (roommate == null) {
                System.out.println("No user with email " + email + " found");
            }
            System.out.println("Would you like to keep searching for a roommate? (Y/N)");

            if (!scanner.nextLine().toUpperCase().equals("Y")) {
                findRoommate = false;
                break;
            }
            } else {
                findRoommate = false;
            }
        }

        try {
            File file = new File(agreementTemplate);
            Scanner readFile = new Scanner(file);
            String currLine = "";
            String newLine = "";
            PrintWriter out = new PrintWriter(saveFile);

            while (readFile.hasNextLine()) {
                currLine = readFile.nextLine();

                if (currLine.contains("<")) {
                    newLine = parseLine(currLine, listing, suite, account);
                } else {
                    newLine = currLine;
                }

                if (newLine.equals("(TENANT X, this will only appear if applicable)")) {
                    if (roommate != null) {
                        out.println(roommate.getName());
                    }
                } else {
                    out.println(newLine);
                }
            }
            out.close();
    } catch (Exception exception) {
      System.out.println("File not found: " + exception);
    }
  }

    /**
     * This method places a rating on a listing.
     * @param listing - listing to rate
     */
    private void rate(Listing listing) {
        System.out.println("You've chosen the following listing to leave a rating about:");
        System.out.println(listing.toString());
        System.out.println("Enter rating.");
        String rate = scanner.nextLine();
        listing.addRating(rate);
    }

    /**
     * This method adds a listing to the system.
     * @param account - account adding the listing
     * @return new listing
     */
    private Listing addListing(Account account) {
        scanner.nextLine();
        System.out.println("Enter information for a new listing.\n");
        System.out.println("Enter title of property: ");
        String title = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter zipcode: ");
        int zip = scanner.nextInt();
        boolean addSuite = true;
        ArrayList<Suite> suites = new ArrayList<Suite>();

        while (addSuite) {
            System.out.println("Enter suite information\n");
            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter number of beds: ");
            int numBeds = scanner.nextInt();
            System.out.println("Enter number of baths: ");
            int numBaths = scanner.nextInt();
            System.out.println("Enter the number of available units: ");
            int numAvailable = scanner.nextInt();
            System.out.println("Enter another suite? (Y/N)");

            if (!scanner.next().toUpperCase().equals("Y")) {
                addSuite = false;
            }
            suites.add(new Suite(price, numBeds, numBaths, numAvailable));
        }
        System.out.println("Enter tags: ");
        boolean addTag = true;
        ArrayList<String> tags = new ArrayList<String>();

        while (addTag) {
            System.out.println("Possible tags:" + "\n\'pool\', " + "\'gym\', " + "\'close\', " +
                    "\'pets\', " + "\n\'wifi\', " + "\'furnished\', " + "\'apartment\', " + "\'washer/dryer\'");

            System.out.println("Enter tags one at a time, or 0 to finish adding tags.");
            String tag = scanner.next();
            if (tag.equals("0")) {
                break;
            }
            tags.add(tag);
            System.out.println(tag + " added!");
        }

        scanner.nextLine();
        System.out.println("Enter lease start date: ");
        String startDate = scanner.nextLine();
        System.out.println("Enter lease end date: ");
        String endDate = scanner.nextLine();
        System.out.println("Enter lease payment address: ");
        String paymentAddress = scanner.nextLine();
        System.out.println("Enter lease damage cost: ");
        String damageCost = scanner.nextLine();
        Listing listing = new Listing(account.getName(), title, address, zip, suites);

        for (String tag : tags) {
            listing.addTag(tag);
        }
        listing.setStartDate(startDate);
        listing.setEndDate(endDate);
        listing.setPaymentAddress(paymentAddress);
        listing.setDamageCost(damageCost);
        listings.addListing(listing);

        return listing;
    }

    /**
     * This method displays the listings associated
     * with an account.
     * @param account - account to display listings for
     */
    public void view(Account account) {
        ArrayList<Listing> list = listings.viewListings(account);

        for (Listing currList : list) {
            System.out.println(currList.toString());
        }
    }

    /**
     * The main method creates an instance of a driver
     * and starts the program.
     * @param args
     */
    public static void main(String[] args) {
        HousingFinderDriver hfd = new HousingFinderDriver();
        hfd.run();
    }
}
