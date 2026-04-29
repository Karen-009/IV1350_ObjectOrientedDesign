package se.kth.iv1350.repairelectricbike.integration;

//import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;
//import se.kth.iv1350.repairelectricbike.model.Amount;

/**
 * Contains all information about a repair order. This is an immutable data
 * transfer object.
 */
public final class RepairOrderDTO {
    private final String id;
    private final String date;
    private final String customersProblemDescription;
    private final String estimatedCompletionDate;
    private final RepairOrderState state;

    /**
     * Creates a new instance.
     * 
     * @param id                          The repair order ID.
     * @param date                        The date the order was created.
     * @param customersProblemDescription Description provided by customer.
     * @param estimatedCompletionDate     Expected finish date.
     * @param state                       The current status of the repair.
     */

    public RepairOrderDTO(String id, String date, String customersProblemDescription,
            String estimatedCompletionDate, RepairOrderState state) {
        this.id = id;
        this.date = date;
        this.customersProblemDescription = customersProblemDescription;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public RepairOrderState getState() {
        return state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
