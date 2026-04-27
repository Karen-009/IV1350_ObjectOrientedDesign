package se.kth.iv1350.repairelectricbike.controller;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;

/**
 * This is the application's only controller class. All calls to the model pass through here.
 */

public class Controller {
    /**
     * Accepts a specific repair order.
     * 
     * @param repairOrderId The ID of the repair order to accept.
     */

    public static void acceptRepairOrder(String repairOrderID) {
        RepairOrder repairOrder = repairOrderIDRegistry.getRepairOrder(repairOrderID);
        repairOrder.acceptRepairOrder();

        repairOrderRegistry.updateRepairOrder(repairOrder);

        RepairOrderDTO orderData = repairOrder.toDTO();
        printer.printRepairOrder(orderData);
    }
}
