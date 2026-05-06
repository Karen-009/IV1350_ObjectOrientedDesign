package se.kth.iv1350.repairelectricbike.integration;

/**
 * Represents the different possible states of a repair order.
 */
public enum RepairOrderState {
    /**
     * The order has been created but not yet reviewed.
     */
    NEWLY_CREATED,

    /**
     * The diagnostics are finished and awaiting customer approval.
     */
    READY_FOR_APPROVAL,

    /**
     * The customer has rejected the repair.
     */
    REJECTED,

    /**
     * The customer has accepted the repair.
     */
    ACCEPTED,

    /**
     * The physical repair work is finished.
     */
    COMPLETED,

    /**
     * The customer has paid for the repair.
     */
    PAYED
}
