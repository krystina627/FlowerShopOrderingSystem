package flowershopordingsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krystina Poling
 */
// Tracks customer order information
abstract class Order {

    protected String orderID;
    protected Calendar dateOfOrder = Calendar.getInstance();
    protected Calendar pickupDate = Calendar.getInstance();
    protected char sizeOfOrder;
    protected String message;
    protected Double orderPrice;

    // Parent constructor to be used by all child classes for manual orders
    public Order(String orderID, String orderDate, String deadlineDate, char sizeOfOrder, String message) {

        try {
            // Format calendar date
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            
            this.orderID = orderID;
            dateOfOrder.setTime(sdf.parse(orderDate));
            pickupDate.setTime(sdf.parse(deadlineDate));
            this.sizeOfOrder = sizeOfOrder;
            if (sizeOfOrder != 'S' && sizeOfOrder != 's' && sizeOfOrder != 'M' && sizeOfOrder != 'm' && sizeOfOrder != 'L' && sizeOfOrder != 'l') {
                throw new InvalidOrderSizeException(sizeOfOrder);
            }
            this.message = message;
            int messageLength = message.length();
            if (messageLength > 20){
                throw new InvalidMessageLengthException(message);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Parent constructor to be used by all child classes for file loaded orders
    public Order(String line) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        orderID = line.substring(line.indexOf("<orderID>") + 9, line.indexOf("</orderID>"));

        // Read in dateOfOrder and pickupDate values as Strings then convert and format them to Calendar objects
        try {
            String date1 = line.substring(line.indexOf("<dateOfOrder>") + 13, line.indexOf("</dateOfOrder>"));
            dateOfOrder.setTime(sdf.parse(date1));

            String date2 = line.substring(line.indexOf("<pickupDate>") + 12, line.indexOf("</pickupDate>"));
            pickupDate.setTime(sdf.parse(date2));

        } catch (ParseException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Save sizeOfOrder as String first and then convert to char
        String sizeString = line.substring(line.indexOf("<sizeOfOrder>") + 13, line.indexOf("</sizeOfOrder>"));
        sizeOfOrder = sizeString.charAt(0);

        if (sizeOfOrder != 'S' && sizeOfOrder != 's' && sizeOfOrder != 'M' && sizeOfOrder != 'm' && sizeOfOrder != 'L' && sizeOfOrder != 'l') {
            throw new InvalidOrderSizeException(sizeOfOrder);
        }

        message = line.substring(line.indexOf("<message>") + 9, line.indexOf("</message>"));
        int messageLength = message.length();
        if (messageLength > 20){
                throw new InvalidMessageLengthException(message);
            }
        orderPrice = Double.parseDouble(line.substring(line.indexOf("<orderPrice>") + 12, line.indexOf("</orderPrice>")));

    }

    abstract public Double calculateOrderPrice();

    public String getOrderID() {
        return orderID;
    }

    public Calendar getDateOfOrder() {
        return dateOfOrder;
    }

    public Calendar getPickupDate() {
        return pickupDate;
    }

    public char getSizeOfOrder() {
        return sizeOfOrder;
    }

    public String getMessage() {
        return message;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setPickupDate(Calendar pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setSizeOfOrder(char sizeOfOrder) {
        this.sizeOfOrder = sizeOfOrder;
            if (sizeOfOrder != 'S' && sizeOfOrder != 's' && sizeOfOrder != 'M' && sizeOfOrder != 'm' && sizeOfOrder != 'L' && sizeOfOrder != 'l') {
                throw new InvalidOrderSizeException(sizeOfOrder);
            }
    }

    public void setMessage(String message) {
        this.message = message;
            int messageLength = message.length();
            if (messageLength > 20){
                throw new InvalidMessageLengthException(message);
            }
    }

    public String convertToString() {

        // Format calendar date 
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        
        String className = this.getClass().getSimpleName();

        String xmlString = "<" + className + "><orderID>" + orderID + "</orderID>"
                + "<dateOfOrder>" + sdf.format(dateOfOrder.getTime()) + "</dateOfOrder>"
                + "<pickupDate>" + sdf.format(pickupDate.getTime()) + "</pickupDate>"
                + "<sizeOfOrder>" + sizeOfOrder + "</sizeOfOrder>"
                + "<message>" + message + "</message>"
                + "<orderPrice>" + orderPrice + "</orderPrice></" + className + ">";
        return xmlString;
    }

}
