package se.kth.iv1350.repairelectricbike.controller;

import se.kth.iv1350.repairelectricbike.model.*;
import se.kth.iv1350.repairelectricbike.integration.*;

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
