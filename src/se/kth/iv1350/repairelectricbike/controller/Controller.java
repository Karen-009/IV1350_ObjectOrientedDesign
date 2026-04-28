package se.kth.iv1350.repairelectricbike.controller;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;
/**
 * This is the application's only controller class. All calls to the model pass through here.
 */

public class Controller {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;
    private RepairOrder repairOrder;

    /**
     * Creates a new instance.
     * 
     * @param creator   Used to get all classes that handle database calls.
     * @param printer   Interface to printer.
     */
    public Controller(RegistryCreator creator, Printer printer){
        this.customerRegistry = creator.getCustomerRegistry();
        this.repairOrderRegistry = creator.getRepairOrderRegistry();
        this.printer = printer;
    }

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
