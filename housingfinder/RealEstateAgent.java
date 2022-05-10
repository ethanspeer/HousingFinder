package housingfinder;
import java.util.ArrayList;

/**
 * RealEstateAgent class contains the methods and details necessary for the creation of a real estate agent account type
 */
public class RealEstateAgent extends Account {

    /**
     * Default constructor initializes instance variables
     * @param email: inherits from account class
     * @param password: inherits from account class
     * @param name: inherits from account class
     */
    public RealEstateAgent(String email, String password, String name) {
        super(email, password, name);
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
