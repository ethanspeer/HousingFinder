package housingfinder;

/**
 * PropertyOwner class contains the methods and details necessary for the creation of a property owner account type
 */
public class PropertyOwner extends Account {

    /**
     * Constructor initializes instance variables
     * @param email: inherits email from account
     * @param password: inherits password from account
     * @param name: inherits name from account
     */
    public PropertyOwner(String email, String password, String name) {
        super(email, password, name);
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
