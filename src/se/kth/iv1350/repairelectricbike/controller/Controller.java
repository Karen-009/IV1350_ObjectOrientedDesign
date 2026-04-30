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

    /**
     * Creates a new repair order and stores it in the repair order registry.
     * 
     * @param problemDesc   Description of the customer's reported problem.
     * @param customerPhone Phone number of the customer placing the repair order.
     * @param bikeSerialNo  Bike serial number of the bike associated with the
     *                      repair order.
     * @return A DTO containing information about the created repair order.
     */
    public RepairOrderDTO createRepairOrder(String problemDesc, String customerPhone, Bike bikeSerialNo) {
        repairOrder = new RepairOrder(problemDesc, customerPhone, bikeSerialNo);
        RepairOrderDTO dto = repairOrder.getRepairOrderDTO();
        repairOrderRegistry.saveRepairOrder(repairOrder);
        return dto;
    }

    /**
     * Finds a customer by phone number.
     * 
     * @param phoneNumber The phone number used to identify the customer.
     * @return A DTO containing the customer information, or null if no customer is
     *         found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        return customerRegistry.findCustomer(phoneNumber);
    }

    /**
     * Finds a repair order connected to a specific customer phone number.
     * 
     * @param phoneNumber The phone number associated with the repair order
     * @return A DTO containing the matching repair order.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber) {
        return repairOrderRegistry.findRepairOrder(phoneNumber);
    }

    /**
     * Retrieves all repair orders.
     * 
     * @return A list containing DTOs for all repair orders.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        return repairOrderRegistry.findAllRepairOrders();
    }

    /**
     * Adds a diagnostic result to a specific repair order.
     * 
     * @param repairOrderId  The ID of the repair order to update.
     * @param diagTaskResult The diagnostic result to add.
     */
    public void addDiagnosticResult(String repairOrderId, DiagnosticTaskDTO diagTaskResult) {
        repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.addDiagnosticResult(diagTaskResult);
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
    }

    /**
     * Adds a repair task to a specific repair order.
     * 
     * @param repairOrderId The ID of the repair order to update.
     * @param repairTask    The repair task to add.
     */
    public void addRepairTask(String repairOrderId, RepairTaskDTO repairTask) {
        repairOrder.addRepairTask(repairTask);
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
    }

    /**
     * Accepts a specific repair order and prints it.
     * 
     * @param repairOrderId The ID of the repair order to accept.
     */

    public void acceptRepairOrder(String repairOrderID) {
        repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderID);
        repairOrder.acceptRepairOrder();
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
        printer.printRepairOrder(repairOrder.getRepairOrderDTO());
    }

    /**
     * Rejects a specific repair order.
     * 
     * @param repairOrderId The ID of the repair order to reject.
     */

    public void rejectRepairOrder(String repairOrderID) {
        repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderID);
        repairOrder.rejectRepairOrder();
        repairOrderRegistry.updateRepairOrder(repairOrder.getRepairOrderDTO());
    }
}