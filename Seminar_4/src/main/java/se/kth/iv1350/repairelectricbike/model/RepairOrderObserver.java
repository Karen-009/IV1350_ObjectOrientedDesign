package se.kth.iv1350.repairelectricbike.model;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;

/**
 * This interface recieves notifications about updated repair orders
 */

public interface RepairOrderObserver {
    /**
     * This method is called when a new repair order is updated
     * 
     * @param repairOrder the updated repair order
     */
    void repairOrderUpdated(RepairOrderDTO repairOrder);
}