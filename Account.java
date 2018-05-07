package flowershopordingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Krystina Poling
 *
 */

/* 
* Account class is used to add, edit, or delete orders from an account. 
* It is also responsible for saving, deleting, and updating the account 
* xml files.
 */
public class Account {

    private long accountNumber;
    private ArrayList<Order> orders = new ArrayList();
    private int totalOrders;
    private Double totalPriceOfOrders;
    private Customer customer;

    // Constructor used when user adds account manually
    public Account(Customer customer) {

        // Create a new customer object
        this.customer = new Customer(customer.getFirstName(), customer.getLasrName(), customer.getEmail(), customer.getPhoneNumber());

        // generate a account number for the new account in milliseconds
        accountNumber = generateAccountNumber();

    }

    //Constructor used when user loads account from file
    public Account(String fileName) {

        try {

            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;

            // read first line and load account level attributes
            line = br.readLine();
            accountNumber = Long.parseLong(line.substring(line.indexOf("<accountNumber>") + 15, line.indexOf("</accountNumber>")));
            totalOrders = Integer.parseInt(line.substring(line.indexOf("<totalOrders>") + 13, line.indexOf("</totalOrders>")));
            totalPriceOfOrders = Double.parseDouble(line.substring(line.indexOf("<totalPriceOfOrders>") + 20, line.indexOf("</totalPriceOfOrders>")));

            // read second line, create new customer object and load customer level attributes
            if (((line = br.readLine()) != null) && (line.substring(0, 10).equals("<customer>"))) {
                Customer customer = new Customer(line);
                this.customer = customer;

            }
            /* Read order lines and create order objects associated with the 
            first xml tag of each line. Keep while loop going until all orders have been read
            and added to the orders arraylist */
            while ((line = br.readLine()) != null) {
                if (line.substring(0, 13).equals("<VasedFlower>")) {
                    VasedFlower vf = new VasedFlower(line);

                    this.orders.add(vf);
                } else if (line.substring(0, 13).equals("<PottedPlant>")) {
                    PottedPlant pp = new PottedPlant(line);

                    this.orders.add(pp);
                } else if (line.substring(0, 16).equals("<BoxOfChocolate>")) {
                    BoxOfChocolate bc = new BoxOfChocolate(line);

                    this.orders.add(bc);

                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Account Constructor Error " + e.getMessage());
        }
    }

    public void addOrder(Order order) {
        // put order object on list of orders
        orders.add(order);
        totalOrders = orders.size();
        Double sum = 00.00;    //temporary variable to store the sum of the prices
        // get each order on the orders list
        for (int x = 0; x < orders.size(); x++) {
            Order ord = (Order) orders.get(x);
            // sum up their prices through the calculate price method
            sum = sum + ord.calculateOrderPrice();
            sum = Math.round(sum * 100.0) / 100.0;
        }
        totalPriceOfOrders = sum;
    }

    public void editOrder(String orderID, Order order) {
        // find the order with the order ID in the orders list
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID().equals(orderID)) // found order
            {
                orders.set(i, order);
                break;  // exit loop
            }
        }
        // recalculate sum of orders with newest order price
        Double sum = 00.00;    //temporary variable to store the sum of the prices

        // get each order on the orders list
        for (int x = 0; x < orders.size(); x++) {
            Order ord = (Order) orders.get(x);
            // sum up their prices through the calculate price method
            sum = sum + ord.calculateOrderPrice();
            sum = Math.round(sum * 100.0) / 100.0;
        }
        totalPriceOfOrders = sum;
    }

    public void saveToFile(String filename) {
        try {
            // create a file given the fileName
            PrintWriter out = new PrintWriter(filename);

            // write out the account level information using the xml format
            out.println("<account>" + "<accountNumber>" + accountNumber + "</accountNumber>"
                    + "<totalOrders>" + totalOrders + "</totalOrders>"
                    + "<totalPriceOfOrders>" + totalPriceOfOrders + "</totalPriceOfOrders>" + "</accountNumber></account>");
            out.println(customer.convetToString());

            // get each order on the orders list
            for (int x = 0; x < orders.size(); x++) {
                // for each order write out the data using the convertToString method  
                Order ord = (Order) orders.get(x);
                out.println(ord.convertToString());
            }

            // close the file
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void deleteAccountFile() {
        // get the current accountNumber and convert to String
        String fileName = Long.toString(getAccountNumber());
        
        // convert the String accountNumber to a filename
        File file = new File(fileName + ".txt");
        
        // delete the account file from the user's hard drive
        if (file.exists()) {
            file.delete();
        }

    }

    public void deleteOrder(String orderID) {
        // find the order with the orderID in the orders list
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID().equals(orderID)) // found order
            {
                // calculate the new total for all orders assigned to the account
                totalPriceOfOrders = totalPriceOfOrders - orders.get(i).getOrderPrice();
                totalPriceOfOrders = Math.round(totalPriceOfOrders * 100.0) / 100.0;
                // subtract one order from the totalOrders attribute
                totalOrders--;
                // delete the order from the orders list
                orders.remove(i);

                break;  // exit loop
            }
        }

    }

    private long generateAccountNumber() {
        long accountNumber = System.currentTimeMillis();
        return accountNumber;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public Double getTotalPriceOfOrders() {
        return totalPriceOfOrders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = new Customer(customer.getFirstName(), customer.getLasrName(), customer.getEmail(), customer.getPhoneNumber());
    }

}
