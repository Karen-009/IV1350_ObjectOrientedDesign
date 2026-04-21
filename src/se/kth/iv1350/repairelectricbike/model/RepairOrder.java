package se.kth.iv1350.repairelectricbike.model;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;

/**
 * Requests a specific repair order.
 */
public class RepairOrder {
    private RepairOrderState state;

    /**
     * Changes the repair order to "Accepted".
     */
    public void acceptRepairOrder() {
        this.state = RepairOrderState.Accepted;
    }

    /**
     * Creates a DTO containing the current state of this order.
     * 
     * @return A snapshot of the order data.
     */
    public RepairOrderDTO toDTO() {
        return new RepairOrderDTO(this.id, this.date, this.customersProblemDescription, this.estimatedCompletionDate,
                this.state);
    }
}
