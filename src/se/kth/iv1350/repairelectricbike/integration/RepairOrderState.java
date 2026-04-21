package se.kth.iv1350.repairelectricbike.integration;

/**
 * Represents the different possible states of a repair order.
 */
public enum RepairOrderState {
    /**
     * The order has been created but not yet reviewed.
     */
    NewlyCreated,

    /**
     * The diagnostics are finished and awaiting customer approval.
     */
    ReadyForApproval,

    /**
     * The customer has rejected the repair.
     */
    Rejected,

    /**
     * The customer has accepted the repair.
     */
    Accepted,

    /**
     * The physical repair work is finished.
     */
    Completed,

    /**
     * The customer has paid for the repair.
     */
    Payed
}
