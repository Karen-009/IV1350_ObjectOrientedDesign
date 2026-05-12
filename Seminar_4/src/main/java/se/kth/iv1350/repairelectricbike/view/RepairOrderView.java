package se.kth.iv1350.repairelectricbike.view;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.RepairOrderObserver;

/**
 * This class shows updated repair order to technicians and receptionists.
 */

public class RepairOrderView implements RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     * Prints the updated repair order to System.out.
     * 
     * @param repairOrder The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
        System.out.println("New repair order created: " + repairOrder);
    }
}