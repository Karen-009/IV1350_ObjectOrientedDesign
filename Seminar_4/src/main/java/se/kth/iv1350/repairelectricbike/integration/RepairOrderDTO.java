package se.kth.iv1350.repairelectricbike.integration;

import se.kth.iv1350.repairelectricbike.model.Amount;

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
    private final Amount totalCost;
    private final Amount discountedTotal;
    private final String discountDescription;

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
        this(id, date, customersProblemDescription, estimatedCompletionDate, state,
                new Amount(0), new Amount(0), "No discount");
    }

    /**
     * Creates a new instance including cost and discount information.
     *
     * @param id                          The repair order ID.
     * @param date                        The date the order was created.
     * @param customersProblemDescription Description provided by customer.
     * @param estimatedCompletionDate     Expected finish date.
     * @param state                       The current status of the repair.
     * @param totalCost                   The total cost before discounts.
     * @param discountedTotal             The total cost after discounts.
     * @param discountDescription         A description of the applied discount.
     */
    public RepairOrderDTO(String id, String date, String customersProblemDescription,
            String estimatedCompletionDate, RepairOrderState state, Amount totalCost,
            Amount discountedTotal, String discountDescription) {
        this.id = id;
        this.date = date;
        this.customersProblemDescription = customersProblemDescription;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.state = state;
        this.totalCost = totalCost;
        this.discountedTotal = discountedTotal;
        this.discountDescription = discountDescription;
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
     * @return The total cost before discounts.
     */
    public Amount getTotalCost() {
        return totalCost;
    }

    /**
     * @return The total cost after discounts.
     */
    public Amount getDiscountedTotal() {
        return discountedTotal;
    }

    /**
     * @return A description of the applied discount.
     */
    public String getDiscountDescription() {
        return discountDescription;
    }
}
