package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
// Keeps track of customer contact information
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Constructor used for manual account creation
    public Customer(String firstName, String lastName, String email, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    // Constructor used when account is loaded from a file
    public Customer(String line) {

        firstName = line.substring(line.indexOf("<firstName>") + 11, line.indexOf("</firstName>"));
        lastName = line.substring(line.indexOf("<lastName>") + 10, line.indexOf("</lastName>"));
        email = line.substring(line.indexOf("<email>") + 7, line.indexOf("</email>"));
        phoneNumber = line.substring(line.indexOf("<phoneNumber>") + 13, line.indexOf("</phoneNumber>"));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLasrName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String convetToString() {
        
        String xmlString = "<customer>" + "<firstName>" + firstName + "</firstName>"
                + "<lastName>" + lastName + "</lastName>"
                + "<email>" + email + "</email>"
                + "<phoneNumber>" + phoneNumber + "</phoneNumber>" + "</customer>";
        
        return xmlString;
    }

}
