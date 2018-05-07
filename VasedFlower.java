
package flowershopordingsystem;

import java.util.Date;

/**
 *
 * @author Krystina Poling
 */
public class VasedFlower extends Order {

    // Constructor for manual orders
    public VasedFlower(String orderID, String orderDate, String deadlineDate, char sizeOfOrder, String message) {
        super(orderID, orderDate, deadlineDate, sizeOfOrder, message);
        
        // call method to calculate order price
        calculateOrderPrice();
    }

    // Constructor for file loaded orders
    public VasedFlower(String line) {
        super(line);
    }

    public Double calculateOrderPrice() {
        // Initialize size of order price variables 
        Double priceSize;

        switch (sizeOfOrder) {
            case 'S':
            case 's':
                priceSize = 15.99;
                break;
            case 'M':
            case 'm':
                priceSize = 25.99;
                break;
            case 'L':
            case 'l':
                priceSize = 35.99;
                break;
            default:
                priceSize = 0.0;
                break;
        }

        //Find difference between pickupDate and orderOfDate
        Date d1 = pickupDate.getTime();
        Date d2 = dateOfOrder.getTime();

        long calcNumDays;
        calcNumDays = d1.getTime() - d2.getTime();
        calcNumDays = calcNumDays / (24 * 60 * 60 * 1000);
        calcNumDays = calcNumDays * 100;

        // Calculate final order price
        if (calcNumDays >= 2) {
            orderPrice = priceSize + 2.99;
            orderPrice = Math.round(orderPrice*100.0)/100.0;
            return orderPrice;
        } else {
            orderPrice = priceSize;
            orderPrice = Math.round(orderPrice*100.0)/100.0;
            return orderPrice;
        }
    }
        
    
    @Override
        public String convertToString() {

        return super.convertToString();
    }  

}
