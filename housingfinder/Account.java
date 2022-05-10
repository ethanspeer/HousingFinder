package housingfinder;
import java.util.ArrayList;

/**
 * This class creates the structure of an account to be
 * followed by it's children: Student, PropertyOwner and RealEstateAgent
 */
public class Account {
    protected String email;
    protected String password;
    protected String name;
    protected String type;
    protected ArrayList<Listing> list;
    protected AccountBackend accountBackend = AccountBackend.getInstance();

    /**
     * This constructor creates an account and adds it to AccountBackend.
     * @param email - email of new account
     * @param password - password of new account
     * @param name - name registered for new account
     */
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        accountBackend.addAccount(this);
    }

    /**
     * This method returns the account's email.
     * @return email
     */
    protected String getEmail() {
        return this.email;
    }

    /**
     * This method returns the account's password.
     * @return password
     */
    protected String getPassword() {
        return this.password;
    }

    /**
     * This method returns the name registered with the account.
     * @return
     */
    protected String getName() {
        return this.name;
    }

    /**
     * This method returns the account type.
     * @return account type
     */
    protected String getType() {

        if (this.getClass().getName() == "housingfinder.Student") {
            return "Student";
        } else if (this.getClass().getName() == "housingfinder.PropertyOwner") {
            return "Property Owner";
        } else if (this.getClass().getName() == "housingfinder.RealEstateAgent") {
            return "Real Estate Agent";
        } else {
            return "account type not found";
        }
    }

    /**
     * This method returns the user's list.
     * @return list
     */
    public ArrayList<Listing> getList() {
        if (this.list == null) {
            this.list = new ArrayList<Listing>();
        }
        return this.list;
    }

    /**
     * This method adds to the user's list.
     * @param listing - listing to be added to the list
     */
    public void addToList(Listing listing) {
        this.list.add(listing);
    }
}
