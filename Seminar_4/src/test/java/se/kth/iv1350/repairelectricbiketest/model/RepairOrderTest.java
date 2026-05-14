package se.kth.iv1350.repairelectricbiketest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.model.Amount;
import se.kth.iv1350.repairelectricbike.model.Bike;
import se.kth.iv1350.repairelectricbike.model.DiagnosticTaskDTO;
import se.kth.iv1350.repairelectricbike.model.LoyaltyDiscountStrategy;
import se.kth.iv1350.repairelectricbike.model.NoDiscountStrategy;
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
        bike = null;
        repairOrder = null;
    }

    @Test
    public void testInitializationState() {
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
    public void testAddRepairTask() {
        RepairTaskDTO task = new RepairTaskDTO("Tighten chain", "Adjust chain tension", new Amount(200),
                RepairTaskState.INCOMPLETE);
        repairOrder.addRepairTask(task);
        assertNotNull(repairOrder.getRepairOrderDTO(), "DTO should still be accessible after adding a task.");
        assertEquals(RepairOrderState.NEWLY_CREATED, repairOrder.getRepairOrderDTO().getState(),
        "State should not change after adding a repair task.");
    }

    @Test
    public void testUpdateStateFromDTO() {
        RepairOrderDTO dto = new RepairOrderDTO("ID", "Date", "Desc", "Date", RepairOrderState.ACCEPTED);
        repairOrder.updateState(dto);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getRepairOrderDTO().getState(),
                "State should be updated from the DTO correctly.");
    }

    @Test
    public void testCalculateTotalCost() {
        repairOrder.addDiagnosticResult(new DiagnosticTaskDTO("Battery", "Battery check", new Amount(200), "Worn"));
        repairOrder.addRepairTask(new RepairTaskDTO("Pads", "Replace pads", new Amount(500), RepairTaskState.INCOMPLETE));

        Amount total = repairOrder.calculateTotalCost();

        assertEquals(700, total.getAmount(), "Total cost should sum all diagnostic and repair tasks.");
    }

    @Test
    public void testCalculateDiscountedTotalWithLoyaltyDiscount() {
        repairOrder.addDiagnosticResult(new DiagnosticTaskDTO("Battery", "Battery check", new Amount(200), "Worn"));
        repairOrder.addRepairTask(new RepairTaskDTO("Pads", "Replace pads", new Amount(500), RepairTaskState.INCOMPLETE));

        Amount discountedTotal = repairOrder.calculateDiscountedTotal(new LoyaltyDiscountStrategy());

        assertEquals(630, discountedTotal.getAmount(), "A 10% loyalty discount should be applied.");
        assertEquals("10% loyalty discount", repairOrder.getRepairOrderDTO().getDiscountDescription(),
                "The used discount strategy should be visible in the DTO.");
    }

    @Test
    public void testCalculateDiscountedTotalWithNoDiscount() {
        repairOrder.addRepairTask(new RepairTaskDTO("Chain", "Adjust chain tension", new Amount(300),
                RepairTaskState.INCOMPLETE));

        Amount discountedTotal = repairOrder.calculateDiscountedTotal(new NoDiscountStrategy());

        assertEquals(300, discountedTotal.getAmount(), "NoDiscountStrategy should not change the total.");
    }
}
