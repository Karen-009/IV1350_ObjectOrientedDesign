package IV1350_ObjectOrientedDesign.tests.se.kth.iv1350.repairelectricbiketest.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;

public class PrinterTest {
    private Printer printer;

    @BeforeEach
    public void setUp() {
        printer = new Printer();
    }

    @AfterEach
    public void tearDown() {
        printer = null;
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
    }
}
