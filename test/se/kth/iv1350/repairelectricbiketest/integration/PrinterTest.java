package se.kth.iv1350.repairelectricbiketest.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;

public class PrinterTest {
    private Printer printer;
    private ByteArrayOutputStream output;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        printer = new Printer();

        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        printer = null;
        output = null;
    }

    @Test
    public void testPrintRepairOrder() {
        RepairOrderDTO dto = new RepairOrderDTO(
                "REQ-123",
                "2024-05-20",
                "Broken chain",
                "2024-05-22",
                RepairOrderState.ACCEPTED);

        printer.printRepairOrder(dto);

        String result = output.toString();

        assertTrue(result.contains("Repair order: REQ-123"),
                "Printer should print the repair order ID.");
    }
}
