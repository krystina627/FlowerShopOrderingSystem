package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
public class InvalidMessageLengthException extends RuntimeException {
    
    private String message;
    
    public InvalidMessageLengthException(String message){
        this.message = message;
    }
    
    public String toString(){
        return this.getClass().getSimpleName() + "Message: " + message + " is too long. Max character length is 20."; 
    }
}
