package se.kth.iv1350.repairelectricbike.integration;

/**
 * Contains all information about a the customer. This is an immutable data
 * transfer object.
 */
public final class CustomerDTO {
    private final String name;
    private final String email;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNo;

    /**
     * Creates a new instance.
     * 
     * @param name          The customer name.
     * @param email         The date the order was created.
     * @param bikeBrand     Description of the customers bikebrand.
     * @param bikeModel     Description of the customers bike model.
     * @param bikeSeriallNo The customer bikes serial number.
     */

    public CustomerDTO(String name, String email, String bikeBrand, String bikeModel, String bikeSeriallNo) {
        this.name = name;
        this.email = email;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNo = bikeSeriallNo;
    }
}
