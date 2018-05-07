package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
// Organizer class interacts with UI 
public class Organizer {

    private Account account;

    // Constructor
    public Organizer() {

    }

    /* 
    * Load Account from File: 
    * filename corresponds to the account number entered 
    * by the user
     */
    public void openFromFile(long accountNumber) {
        String filename = accountNumber + ".txt";      
        account = new Account(filename);
        
    }

    // Calls Account constructor for creating a new account manually
    public void createAccount(Customer customer) {
        account = new Account(customer);
    }

    // filename corresponds to the Account's account number
    public void saveToFile() {
        String filename;
        filename = Long.toString(account.getAccountNumber()) + ".txt";
        account.saveToFile(filename);
    }

    // delete the current account object and account text file
    public void deleteAccount() {

        account.deleteAccountFile();
        account = null;

    }

    public void addOrder(Order order) {
        account.addOrder(order);
    }

    public void editOrder(String orderID, Order order) {
        account.editOrder(orderID, order);
    }

    public void cancelOrder(String orderID) {
        account.deleteOrder(orderID);
    }

    public void editCustomer(Customer customer) {
        account.setCustomer(customer);
    }

    public Account getAccount() {
        return account;

    }

}
