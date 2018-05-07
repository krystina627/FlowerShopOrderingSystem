 package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
public class InvalidTypeException extends RuntimeException {

    private String orderType;

    public InvalidTypeException(String orderType) {

        this.orderType = orderType;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "Wrong Order Type: " + orderType + "  --Please enter correct order type.";

    }
}
