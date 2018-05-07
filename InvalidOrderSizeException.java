package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
public class InvalidOrderSizeException extends RuntimeException {

    private char orderSize;

    public InvalidOrderSizeException(char orderSize) {
        this.orderSize = orderSize;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": " + orderSize + " is incorrect. Please enter S, M, or L for order size";
    }

}
