package se.kth.iv1350.repairelectricbike.controller;

import java.util.List;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.model.RepairTaskDTO;
import se.kth.iv1350.repairelectricbike.model.DiagnosticTaskDTO;
import se.kth.iv1350.repairelectricbike.model.Bike;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;
import se.kth.iv1350.repairelectricbike.integration.CustomerDTO;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */

public class Controller {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;
    private RepairOrder repairOrder;

    /**
     * Creates a new instance.
     * 
     * @param creator Used to get all classes that handle database calls.
     * @param printer Interface to printer.
     */
    public Controller(RegistryCreator creator, Printer printer) {
        this.customerRegistry = creator.getCustomerRegistry();
        this.repairOrderRegistry = creator.getRepairOrderRegistry();
        this.printer = printer;
    }

    public RepairOrderDTO createRepairOrder(String problemDesc, String customerPhone, Bike bikeSerialNo) {
        repairOrder = new RepairOrder(problemDesc, customerPhone, bikeSerialNo);
        RepairOrderDTO dto = repairOrder.getRepairOrderDTO();
        repairOrderRegistry.saveRepairOrder(dto);
        return dto;
    }

    public CustomerDTO findCustomer(String phoneNumber) {
        return CustomerRegistry.findCustomer(phoneNumber);
    }

    public RepairOrderDTO findRepairOrder(String phoneNumber){
        return repairOrderRegistry.findRepairOrder(phoneNumber);
    }

    public List<RepairOrderDTO> findAllRepairOrders() {
        return repairOrderRegistry.findAllRepairOrders();
    }

    public void addDiagnosticResult(String repairOrderId, DiagnosticTaskDTO diagTaskResult) {
        repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.addDiagnosticResult(diagTaskResult);
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
    }

    public void addRepairTask(String repairOrderId, RepairTaskDTO repairTask) {
        repairOrder.addRepairTask(repairTask);
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
    }

    /**
     * Accepts a specific repair order.
     * 
     * @param repairOrderId The ID of the repair order to accept.
     */

    public void acceptRepairOrder(String repairOrderID) {
        repairOrder.acceptRepairOrder(repairOrderRegistry);
        printer.printRepairOrder(repairOrder.getRepairOrderDTO());
    }
}