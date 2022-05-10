package housingfinder;
import java.util.ArrayList;

/**
 * This class is a singleton that stores the account backend in system memory
 * and loads and writes accounts to json.
 */
public class AccountBackend {
    private static ArrayList<Account> validList;
    private DataLoader load;
    private DataWriter write;
    private static AccountBackend accountBackend;

    /**
     * The constructor is private because only one instance can be made.
     */
    private AccountBackend() {}

    /**
     * This method is accessed to get the instance of
     * AccountBackend from other classes. If an instance
     * does not already exist, one is created.
     * @return accountBackend - instance of AccountBackend class
     */
    public static AccountBackend getInstance() {

        if (accountBackend == null) {
            accountBackend = new AccountBackend();
        }

        return accountBackend;
    }

    /**
     * This method loads the json data into the Account Backend.
     */
    public void loadJSON() {
        if (validList == null) {
            validList = new ArrayList<Account>();
        }

        if (load == null) {
            load = new DataLoader();
        }
        validList = load.fillAccounts();
    }

    /**
     * This method updates the account data to the json file.
     */
    public void updateJSON() {

        if (validList != null) {

            if (write == null) {
                write = new DataWriter();
            }
            write.updateAccounts(validList);
        } else {
            System.out.println("No accounts to save");
        }
    }

    /**
     * This method adds an account to the list of accounts.
     * @param account - account to be added to the list
     */
    public void addAccount(Account account) {

        if (validList == null) {
            validList = new ArrayList<Account>();
        }
        validList.add(account);
    }

    /**
     * This method deletes an account from account backend.
     * @param account - account to be deleted
     */
    public void deleteAccount(Account account) {
        if (validList != null) {

            for (Account a : validList) {

                if (a.equals(account)) {
                    validList.remove(account);
                }
            }
        }
    }

    /**
     * This method checks if an account is in a valid list.
     * @param email - email of the account to be validated
     * @param password - password of the account to be validated
     * @return true | false - whether or not account is valid
     */
    public boolean validateAccount(String email, String password) {

        if (validList == null) {
            return false;
        }

        for (Account a : validList) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method authenticates the user and logs them in.
     * @param email - user email
     * @param password - user password
     * @return account that has been logged in
     */
    public Account logIn(String email, String password) {

        for (Account a : validList) {

            if (validateAccount(email, password)) {
                return a;
            }
        }
        return null;
    }

    /**
     * This method returns the list of all accounts.
     * @return validList - list of all accounts
     */
    public ArrayList<Account> getValidList() {
        return this.validList;
    }

    /**
     * This method returns an account associated with an email.
     * @param email - the email of the returned account
     * @return account that matches the given email
     */
    public Account getAccount(String email) {

        for (Account account : validList) {

            if (email.equals(account.getEmail())) {

                return account;
            }
        }

        return null;
    }
}
