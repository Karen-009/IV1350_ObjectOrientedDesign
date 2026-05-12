package se.kth.iv1350.repairelectricbike.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.RepairOrderObserver;

/**
 * Logs repair order updates to a file.
 * Implements {@link RepairOrderObserver} and is notified
 * whenever a repair order is updated.
 * The log file is called repairorder-log.txt and is located
 * in the current directory.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private PrintWriter logStream;

    /**
     * Creates a new instance and opens the log file.
     */
    public RepairOrderLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("repairorder-log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG REPAIR ORDERS.");
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the repair order contents to the log file.
     *
     * @param repairOrder The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
        logStream.println("=== Repair Order Updated ===");
        logStream.println("ID:          " + repairOrder.getId());
        logStream.println("Date:        " + repairOrder.getDate());
        logStream.println("Problem:     " + repairOrder.getCustomersProblemDescription());
        logStream.println("Est. Finish: " + repairOrder.getEstimatedCompletionDate());
        logStream.println("State:       " + repairOrder.getState());
        logStream.println("============================");
        logStream.println();
    }
}