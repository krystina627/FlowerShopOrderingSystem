package flowershopordingsystem;

/**
 *
 * @author Krystina Poling
 */
public class PottedPlant extends Order {

    private String plantType;

    // Constructor for manually inputted Potted Plant orders
    PottedPlant(String orderID, String orderDate, String deadlineDate, char sizeOfOrder, String message, String plantType) {

        super(orderID, orderDate, deadlineDate, sizeOfOrder, message);
        // check plant type
        if (plantType.equalsIgnoreCase("fern") || plantType.equalsIgnoreCase("aloe") || plantType.equalsIgnoreCase("cactus")) {
            this.plantType = plantType;

            // call method to calculate order price
            calculateOrderPrice();
        } else {
            throw new InvalidTypeException(plantType);
        }

    }

    // Constructor for file loaded orders
    public PottedPlant(String line) {
        super(line);
        plantType = line.substring(line.indexOf("<plantType>") + 11, line.indexOf("</plantType>"));
        // check plant type 
        if (plantType.equalsIgnoreCase("fern") || plantType.equalsIgnoreCase("aloe") || plantType.equalsIgnoreCase("cactus")) {

            // call method to calculate order price
            calculateOrderPrice();
        } else {
            throw new InvalidTypeException(plantType);
        }

    }

    @Override
    public Double calculateOrderPrice() {

        switch (sizeOfOrder) {
            case 'S':
            case 's':
                orderPrice = 19.99;
                break;
            case 'M':
            case 'm':
                orderPrice = 29.99;
                break;
            case 'L':
            case 'l':
                orderPrice = 39.99;
                break;
            default:
                orderPrice = 0.0;
                break;
        }
        orderPrice = Math.round(orderPrice * 100.0) / 100.0;
        return orderPrice;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        if (plantType.equalsIgnoreCase("fern") || plantType.equalsIgnoreCase("aloe") || plantType.equalsIgnoreCase("cactus")) {
            this.plantType = plantType;

        } else {
            throw new InvalidTypeException(plantType);
        }
    }

    @Override
    public String convertToString() {

        return super.convertToString() + "<plantType>" + plantType + "</plantType>";

    }

}
