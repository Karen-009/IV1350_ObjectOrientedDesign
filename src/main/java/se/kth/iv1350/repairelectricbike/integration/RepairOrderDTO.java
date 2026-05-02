package se.kth.iv1350.repairelectricbike.integration;

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

    /**
     * @return The repair order ID.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The current state of the repair order.
     */
    public RepairOrderState getState() {
        return state;
    }  
    
    /**
     * @return The date the repair order was created.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return The customer's problem description.
     */
    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    /**
     * @return The estimated completion date.
     */
    public String getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    /**
     * Returns a string representation of this repair order DTO.
     * The returned string contains selected repair order information,
     * including the ID, current state, and customer's problem description.
     *
     * @return A string describing this repair order DTO.
     */
    @Override
    public String toString() {
        return "RepairOrder[id=" + id
               + ", state=" + state
               + ", problem=" + customersProblemDescription + "]";
    }
}