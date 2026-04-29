package se.kth.iv1350.repairelectricbike.integration;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;

/**
 * The interface to the printer, used for all printouts initiated by this
 * program.
 */

public class Printer {
    /**
     * Prints the specified repairOrder. This dummy implementation prints to
     * <code>System.out</code> instead of a printer.
     * 
     * @param repairOrder
     */
    public void printRepairOrder(RepairOrderDTO repairOrder) {
        System.out.println("Repair order: " + repairOrder.getId());
    }
}
