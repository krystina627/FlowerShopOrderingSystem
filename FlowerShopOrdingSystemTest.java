package flowershopordingsystem;

import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Krystina Poling
 */
public class FlowerShopOrdingSystemTest {

    public static void main(String[] args) throws ParseException {

        FlowerShopOrdingSystemTest t = new FlowerShopOrdingSystemTest();

        t.testVasedFlower();
        t.testPottedPlant();
        t.testBoxOfChocolate();

        t.testAccount();
        t.testMuiltpleOrders();
        t.testSaveToFile();
        t.testLoadAccountAndDeleteAccount();
        t.testFourOrdersAndDelete();
        t.editAllThreeTypesOfOrders();
        t.testInvalidOrderSizeException();
        t.testInvalidMessageLengthException();
        t.testPlantInvalidTypeException();
        t.testChocolateInvalidTypeException();
    }

    public void testAccount() {
        Customer cust = new Customer("Stacy", "Thompson", "SThomp@gmail.com", "443-293-1931");
        Account a = new Account(cust);
        VasedFlower vf = new VasedFlower("VF3827", "02-14-2018", "02-16-2018", 'M', "Happy Birthday");
        a.addOrder(vf);
        PottedPlant pp = new PottedPlant("PP84921", "04-15-2018", "04-15-2018", 'L', "Get Well Soon", "fern");
        a.addOrder(pp);
        BoxOfChocolate bc = new BoxOfChocolate("BC2938", "01-22-2018", "01-24-2018", 'S', "Happy Anniversary", "dark");
        a.addOrder(bc);

        System.out.println("Account test");
        ArrayList<Order> current = a.getOrders();
        for (int i = 0; i < current.size(); i++) // get every order
        {
            System.out.println(current.get(i).convertToString());
        }

        System.out.println("Toal Orders: " + a.getTotalOrders());
        System.out.println("Total Price = " + a.getTotalPriceOfOrders());
        System.out.println("");

    }

    public void testVasedFlower() {

        try {
            VasedFlower vf = new VasedFlower("VF92838", "12-12-2017", "12-15-2017", 'L', "Happy Holidays!!!");

            System.out.println("Vased Flower Order test");
            System.out.println("Order String = " + vf.convertToString());
            System.out.println("Price = " + vf.calculateOrderPrice());
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testPottedPlant() {

        try {
            PottedPlant pp = new PottedPlant("PP48292", "06-27-2017", "06-29-2017", 'M', "Welcome Home", "cactus");

            System.out.println("Potted Plant Order test");
            System.out.println("Order String = " + pp.convertToString());
            System.out.println("Price = " + pp.calculateOrderPrice());
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testBoxOfChocolate() {

        try {
            BoxOfChocolate bc = new BoxOfChocolate("BC99384", "03-28-2018", "03-28-3013", 's', "Happy Birthday!!!", "white");

            System.out.println("Box of Chocolate Order test");
            System.out.println("Order String = " + bc.convertToString());
            System.out.println("Price = " + bc.calculateOrderPrice());
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testMuiltpleOrders() {
        try {
            Customer cust = new Customer("Ben", "Murphy", "BMurphy293@gmail.com", "429-293-1938");
            Account a = new Account(cust);
            a.addOrder(new VasedFlower("VF28301", "05-19-2016", "05-22-2016", 'M', "Happy Birthday"));
            a.addOrder(new VasedFlower("VF99847", "02-10-2016", "02-11-2016", 'l', "I love you!"));
            a.addOrder(new PottedPlant("PP12938", "04-20-2018", "04-28-2018", 's', "Get Well Soon", "aloe"));
            a.addOrder(new PottedPlant("PP84921", "011-15-2017", "11-18-2017", 'L', "I Miss You", "fern"));
            a.addOrder(new PottedPlant("PP88273", "06-12-2017", "06-19-2017", 'm', "Happy Summer", "CACTUS"));
            a.addOrder(new BoxOfChocolate("BC29381", "07-22-2016", "07-23-2016", 'S', "Happy Anniversary", "dark"));
            a.addOrder(new BoxOfChocolate("BC33495", "12-21-2017", "12-23-2017", 'm', "Merry Christmas", "MILK"));
            a.addOrder(new BoxOfChocolate("BC20394", "01-01-2018", "01-01-2018", 'L', "Happy New Year", "White"));

            System.out.println("Test Muiltple Orders with Various Input:");
            ArrayList<Order> current = a.getOrders();
            for (int i = 0; i < current.size(); i++) // get every order
            {
                System.out.println(current.get(i).convertToString());
            }

            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testSaveToFile() {
        {
            try {
                Organizer org = new Organizer();
                Customer cust = new Customer("Londyn", "Smith", "LSmith@gmail.com", "329-293-2939");
                org.createAccount(cust);

                Account a = org.getAccount();

                a.addOrder(new VasedFlower("VF28301", "05-19-2016", "05-22-2016", 'M', "Happy Birthday"));
                a.addOrder(new VasedFlower("VF99847", "02-10-2016", "02-11-2016", 'l', "I love you!"));
                a.addOrder(new PottedPlant("PP12938", "04-20-2018", "04-28-2018", 's', "Get Well Soon", "aloe"));
                a.addOrder(new PottedPlant("PP84921", "011-15-2017", "11-18-2017", 'L', "I Miss You", "fern"));
                a.addOrder(new PottedPlant("PP88273", "06-12-2017", "06-19-2017", 'm', "Happy Summer", "CACTUS"));
                a.addOrder(new BoxOfChocolate("BC29381", "07-22-2016", "07-23-2016", 'S', "Happy Anniversary", "dark"));
                a.addOrder(new BoxOfChocolate("BC33495", "12-21-2017", "12-23-2017", 'm', "Merry Christmas", "MILK"));
                a.addOrder(new BoxOfChocolate("BC20394", "01-01-2018", "01-01-2018", 'L', "Happy New Year", "White"));

                org.saveToFile();  // current directory

                System.out.println("Create Account and Save to File:");

                ArrayList<Order> current = a.getOrders();
                for (int i = 0; i < current.size(); i++) // get every order
                {
                    System.out.println(current.get(i).convertToString());
                }

                System.out.println("Toal Orders: " + a.getTotalOrders());
                System.out.println("Total Price = " + a.getTotalPriceOfOrders());
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("");
        }
    }

    public void testLoadAccountAndDeleteAccount() {
        try {
            Organizer org = new Organizer();
            
            /* current parameter is file located in code directory. If tested 
            more then once openFromFile parameter needs to be changed */
            org.openFromFile(1524014093823L);

            System.out.println("Load Account from File and then Delete File");

            Account a = org.getAccount();

            ArrayList<Order> current = a.getOrders();
            String order;
            for (int z = 0; z < current.size(); z++) {
                order = current.get(z).convertToString();
                System.out.println(order);
            }
            
            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());

            org.deleteAccount();           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("");
    }

    public void testFourOrdersAndDelete() {
        try {
            Organizer org = new Organizer();
            Customer cust = new Customer("Londyn", "Smith", "LSmith@gmail.com", "329-293-2939");
            org.createAccount(cust);

            Account a = org.getAccount();

            a.addOrder(new VasedFlower("VF28301", "05-19-2016", "05-22-2016", 'M', "Happy Birthday"));

            a.addOrder(new PottedPlant("PP12938", "04-20-2018", "04-28-2018", 's', "Get Well Soon", "aloe"));

            a.addOrder(new PottedPlant("PP88273", "06-12-2017", "06-19-2017", 'm', "Happy Summer", "CACTUS"));

            a.addOrder(new BoxOfChocolate("BC33495", "12-21-2017", "12-23-2017", 'm', "Merry Christmas", "MILK"));

            org.saveToFile(); // current directory

            System.out.println("Create Account with 4 Orders and Delete All: ");

            ArrayList<Order> current = a.getOrders();
            for (int i = 0; i < current.size(); i++) // get every order
            {
                System.out.println(current.get(i).convertToString());
            }

            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");

            System.out.println("Start Deletes");
            System.out.println("");

            org.cancelOrder("VF28301");
            System.out.println("First Order Deleted:");
            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");

            org.cancelOrder("PP12938");
            System.out.println("Secend Order Deleted:");
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");

            org.cancelOrder("PP88273");
            System.out.println("Third Order Deleted:");
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");

            org.cancelOrder("BC33495");
            System.out.println("All Orders Deleted:");
            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());
            System.out.println("");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("");
    }

    public void editAllThreeTypesOfOrders() {
        try {
            Organizer org = new Organizer();
            // Current openFromFile parameter is associated with text file located in source code file
            org.openFromFile(1524048189308L);

            System.out.println("Edit all Three Types of Orders:");
            System.out.println(" ");

            Account a = org.getAccount();

            System.out.println("Orders Loaded From File:");
            ArrayList<Order> current = a.getOrders();
            for (int i = 0; i < current.size(); i++) // get every order
            {
                System.out.println(current.get(i).convertToString());
            }

            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());

            a.editOrder("VF28301", new VasedFlower("VF28301", "05-19-2016", "05-19-2016", 'L', "Happy Birthday Baby"));
            a.editOrder("PP12938", new PottedPlant("PP12938", "04-20-2018", "04-22-2018", 'm', "Get Well Soon", "Fern"));
            a.editOrder("BC33495", new BoxOfChocolate("BC33495", "12-22-2017", "12-24-2017", 'S', "Happy Holidays", "Milk"));

            System.out.println(" ");
            System.out.println("Orders After Edit:");
            current = a.getOrders();
            for (int i = 0; i < current.size(); i++) // get every order
            {
                System.out.println(current.get(i).convertToString());
            }

            System.out.println("Toal Orders: " + a.getTotalOrders());
            System.out.println("Total Price = " + a.getTotalPriceOfOrders());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("");
    }

    public void testInvalidOrderSizeException() {
        try {
            BoxOfChocolate bc = new BoxOfChocolate("BC99384", "03-28-2018", "03-28-3013", 'B', "Happy Birthday!!!", "white");

            System.out.println("Test for InvalidOrderSizeException:");
            System.out.println("Order String = " + bc.convertToString());
            System.out.println("Price = " + bc.calculateOrderPrice());
            System.out.println("");
        } catch (InvalidOrderSizeException s) {
            System.out.println("Error " + s.toString());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testInvalidMessageLengthException() {
        try {
            VasedFlower vf = new VasedFlower("VF92838", "12-12-2017", "12-15-2017", 'L', "Happy Holidays Mom and Dad!!!");

            System.out.println("Test for InvalidMessageLengthException:");
            System.out.println("Order String = " + vf.convertToString());
            System.out.println("Price = " + vf.calculateOrderPrice());
            System.out.println("");
        } catch (InvalidMessageLengthException m) {
            System.out.println("Error " + m.toString());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void testPlantInvalidTypeException() {

        try {
            PottedPlant pp = new PottedPlant("PP48292", "06-27-2017", "06-29-2017", 'M', "Welcome Home", "rose");

            System.out.println("Test for InvalidTypeException for Plant Order:");
            System.out.println("Order String = " + pp.convertToString());
            System.out.println("Price = " + pp.calculateOrderPrice());
            System.out.println("");
        } catch (InvalidTypeException p) {
            System.out.println("Error " + p.toString());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void testChocolateInvalidTypeException() {

        try {
            BoxOfChocolate bc = new BoxOfChocolate("BC99384", "03-28-2018", "03-28-3013", 's', "Happy Birthday!!!", "Cherry");

            System.out.println("Test for InvaidTypeException for Box of Chocolate Order:");
            System.out.println("Order String = " + bc.convertToString());
            System.out.println("Price = " + bc.calculateOrderPrice());
            System.out.println("");
        } catch (InvalidTypeException c) {
            System.out.println("Error " + c.toString());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
