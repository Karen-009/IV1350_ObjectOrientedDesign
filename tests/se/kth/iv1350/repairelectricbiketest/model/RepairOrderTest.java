package IV1350_ObjectOrientedDesign.tests.se.kth.iv1350.repairelectricbiketest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.Amount;
import se.kth.iv1350.repairelectricbike.model.Bike;
import se.kth.iv1350.repairelectricbike.model.DiagnosticTaskDTO;
import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.model.RepairTaskDTO;
import se.kth.iv1350.repairelectricbike.model.RepairTaskState;

public class RepairOrderTest {
    private RepairOrder repairOrder;
    private String problemDesc = "Wheel is stuck";
    private String phone = "0701234567";
    private Bike bike;

    @BeforeEach
    public void setUp() {
        bike = new Bike("BIKE", "X09", "99");
        repairOrder = new RepairOrder(problemDesc, phone, bike);
    }

    @AfterEach
    public void tearDown() {
        // Methodology: Explicitly set references to null to ensure isolation.
        bike = null;
        repairOrder = null;
    }

    @Test
    public void testInitilizationState() {
        RepairOrderDTO dto = repairOrder.getRepairOrderDTO();
        assertEquals(RepairOrderState.NEWLY_CREATED, dto.getState(),
                "A new repair order should have state NEWLY_CREATED.");
        assertNotNull(repairOrder.getId(), "A unique ID should be generated upon creation.");
    }

    @Test
    public void testAcceptRepairOrder() {
        repairOrder.acceptRepairOrder();
        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getRepairOrderDTO().getState(),
                "State should change to ACCEPTED.");
    }

    @Test
    public void testRejectRepairOrder() {
        repairOrder.rejectRepairOrder();
        assertEquals(RepairOrderState.REJECTED, repairOrder.getRepairOrderDTO().getState(),
                "State should change to REJECTED.");
    }

    @Test
    public void testAddDiagnosticResult() {
        DiagnosticTaskDTO diag = new DiagnosticTaskDTO("Faulty battery", "Battery check", new Amount(1000), "Failed");
        repairOrder.addDiagnosticResult(diag);

        RepairOrderState actualState = repairOrder.getRepairOrderDTO().getState();
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, actualState,
                "Adding a diagnostic result should change state to READY_FOR_APPROVAL.");
    }

    @Test
    public void testAddRepairTaskU() {
        RepairTaskDTO task = new RepairTaskDTO("Tighten chain", "Adjust chain tension", new Amount(200),
                RepairTaskState.INCOMPLETE);
        repairOrder.addRepairTask(task);
        assertNotNull(repairOrder.getRepairOrderDTO(), "DTO should still be accessible after adding a task.");
    }

    @Test
    public void testUpdateStateFromDTO() {
        RepairOrderDTO dto = new RepairOrderDTO("ID", "Date", "Desc", "Date", RepairOrderState.ACCEPTED);
        repairOrder.updateState(dto);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getRepairOrderDTO().getState(),
                "State should be updated from the DTO correctly.");
    }
}
