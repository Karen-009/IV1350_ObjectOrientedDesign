package se.kth.iv1350.repairelectricbike.model;

/**
 * Represents the bike that is being repaired. 
 */
public class Bike {
    private final String brand;
    private final String model;
    private final String serialNo;
    /**
     * Creates a new instance.
     *
     * @param brand    The bike brand.
     * @param model    The bike model.
     * @param serialNo The bike serial number.
     */
    public Bike(String brand, String model, String serialNo) {
        this.brand = brand;
        this.model = model;
        this.serialNo = serialNo;
    }

    /**
     * @return The bike brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return The bike model.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return The bike serial number.
     */
    public String getSerialNo() {
        return serialNo;
    }
}