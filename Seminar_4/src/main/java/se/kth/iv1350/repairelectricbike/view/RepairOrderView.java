package se.kth.iv1350.repairelectricbike.view;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.RepairOrderObserver;

/**
 * This class shows updated repair order to technicians and receptionists.
 */

public class RepairOrderView implements RepairOrderObserver {
/**
 * prints the updated repair order to system.out
 * @param repairOrder the updated repair order
 */

    @Override
    public void newRepairOrder(RepairOrderDTO repairOrder) {
        System.out.println("New repair order created: " + repairOrder);
    }
}