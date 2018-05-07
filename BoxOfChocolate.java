package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
public class BoxOfChocolate extends Order {

    private String chocolateType;

    // Constructor for maunally inputted orders
    BoxOfChocolate(String orderID, String orderDate, String deadlineDate, char sizeOfOrder, String message, String chocolateType) {

        super(orderID, orderDate, deadlineDate, sizeOfOrder, message);
        // check chocolate type
        if (chocolateType.equalsIgnoreCase("milk") || chocolateType.equalsIgnoreCase("dark") || chocolateType.equalsIgnoreCase("white")) {
            this.chocolateType = chocolateType;
        } else {
            throw new InvalidTypeException(chocolateType);
        }
    }

    // Constructor for file loaded orders
    public BoxOfChocolate(String line) {
        super(line);
        chocolateType = line.substring(line.indexOf("<chocolateType>") + 15, line.indexOf("</chocolateType>"));
        // check choclate type
        if (!(chocolateType.equalsIgnoreCase("milk") || chocolateType.equalsIgnoreCase("dark") || chocolateType.equalsIgnoreCase("white"))) {
            // call method to calculate order price         

            throw new InvalidTypeException(chocolateType);
        }

    }

    public Double calculateOrderPrice() {
        Double chocolatePrice = null;

        if (chocolateType.equalsIgnoreCase("milk")) {
            chocolatePrice = 10.00;
        }
        if (chocolateType.equalsIgnoreCase("dark")) {
            chocolatePrice = 15.00;
        }
        if (chocolateType.equalsIgnoreCase("white")) {
            chocolatePrice = 20.00;
        }

        switch (sizeOfOrder) {
            case 'S':
            case 's':
                orderPrice = chocolatePrice;
                break;
            case 'M':
            case 'm':
                orderPrice = chocolatePrice + 5.99;
                break;
            case 'L':
            case 'l':
                orderPrice = chocolatePrice + 10.99;
                break;
            default:
                orderPrice = 0.0;
                break;
        }
        orderPrice = Math.round(orderPrice * 100.0) / 100.0;
        return orderPrice;

    }

    public String getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(String chocolateType) {
        if (chocolateType.equalsIgnoreCase("milk") || chocolateType.equalsIgnoreCase("dark") || chocolateType.equalsIgnoreCase("white")) {
            this.chocolateType = chocolateType;
        } else {
            throw new InvalidTypeException(chocolateType);
        }
    }

    @Override
    public String convertToString() {

        return super.convertToString() + "<chocolateType>" + chocolateType
                + "</chocolateType>";
    }
}
