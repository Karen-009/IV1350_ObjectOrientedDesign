package se.kth.iv1350.repairelectricbike.view;

import java.util.List;

import se.kth.iv1350.repairelectricbike.controller.Controller;
import se.kth.iv1350.repairelectricbike.integration.CustomerDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.Amount;
import se.kth.iv1350.repairelectricbike.model.Bike;
import se.kth.iv1350.repairelectricbike.model.DiagnosticTaskDTO;
import se.kth.iv1350.repairelectricbike.model.RepairTaskDTO;
import se.kth.iv1350.repairelectricbike.model.RepairTaskState;

/**
 * This program has no real user interface. Instead, this
 * class simulates user interactions with hardcoded calls to all system operations.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller used for all operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a user interaction that generates calls to
     * all system operations in the basic flow. All returned
     * values are printed to System.out.
     */
    public void sampleExecution() {

        CustomerDTO customer = contr.findCustomer("0701234567");
        System.out.println("Found customer: " + customer);

        Bike bike = new Bike("Trek", "FX3", "SN123456");
        RepairOrderDTO order = contr.createRepairOrder(
            "Brakes not working", "0701234567", bike);
        System.out.println("Created repair order: " + order);

        DiagnosticTaskDTO diag = new DiagnosticTaskDTO(
            "BrakeCheck",
            "Inspected brake pads and cables",
            new Amount(200),
            "Brake pads worn out"
        );
        contr.addDiagnosticResult(order.getId(), diag);
        System.out.println("Added diagnostic result.");

        RepairTaskDTO task = new RepairTaskDTO(
            "ReplaceBrakePads",
            "Replace front and rear brake pads",
            new Amount(500),
            RepairTaskState.INCOMPLETE
        );
        contr.addRepairTask(order.getId(), task);
        System.out.println("Added repair task.");

        contr.acceptRepairOrder(order.getId());
        System.out.println("Repair order accepted.");

        RepairOrderDTO found = contr.findRepairOrder("0701234567");
        System.out.println("Found repair order: " + found);

        List<RepairOrderDTO> all = contr.findAllRepairOrders();
        System.out.println("All repair orders: " + all);
    }
}
