package se.kth.iv1350.repairelectricbiketest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import se.kth.iv1350.repairelectricbike.model.Amount;
import se.kth.iv1350.repairelectricbike.model.Bike;
import se.kth.iv1350.repairelectricbike.integration.Printer;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.controller.Controller;
import se.kth.iv1350.repairelectricbike.model.DiagnosticTaskDTO;
import se.kth.iv1350.repairelectricbike.model.RepairTaskDTO;
import se.kth.iv1350.repairelectricbike.integration.CustomerDTO;
import se.kth.iv1350.repairelectricbike.integration.CustomerPhoneNumberNotFoundException;
import se.kth.iv1350.repairelectricbike.integration.DatabaseConnectionFailureException;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;
import se.kth.iv1350.repairelectricbike.model.RepairTaskState;

public class ControllerTest {
    private Controller controller;
    private RegistryCreator creator;
    private Printer printer;

    /**
     * Initialize fresh objects for every test to ensure independence.
     */
    @BeforeEach
    public void setUp() {
        creator = new RegistryCreator();
        printer = new Printer();
        controller = new Controller(creator, printer);
    }

    /**
     * Clean up references to help the garbage collector.
     */
    @AfterEach
    public void tearDown() {
        controller = null;
        creator = null;
        printer = null;
    }

    /**
     * Test for createRepairOrder
     * Should return a RepairOrderDTO and save it in RepairOrderRegistry
     */
    @Test
    public void testCreateRepairOrder() {
        String desc = "Flat tire";
        String phone = "0701234567";
        Bike bike = new Bike("GIANT", "SNS", "01");

        RepairOrderDTO newOrder = controller.createRepairOrder(desc, phone, bike);
        assertNotNull(newOrder, "RepairOrderDTO should not be null");
        assertEquals(desc, newOrder.getCustomersProblemDescription(), "DTO contains incorrect description.");

        RepairOrderDTO savedOrder = controller.findRepairOrder(phone);
        assertNotNull(savedOrder, "The order should be found in the registry after creation");
    }

    @Test
    public void testFindCustomerFound() throws CustomerPhoneNumberNotFoundException {
        String phoneNumber = "0701234567";
        CustomerDTO result = controller.findCustomer(phoneNumber);
        assertNotNull(result, "Should find a customer that exists in the registry.");
    }

    @Test
    public void testFindCustomerNotFound() {
        String nonExistingPhone = "999999999";

        try {
            controller.findCustomer(nonExistingPhone);
            fail("Expected CustomerPhoneNumberNotFoundException was not thrown.");
        } catch (CustomerPhoneNumberNotFoundException exc) {
            assertTrue(exc.getMessage().contains(nonExistingPhone),
            "Exception message should contain the searched phone number.");
        }
    }

    @Test
    public void testFindCustomerDatabaseFailure() {
        String hardcodedFailurePhone = "0700000000";

        try {
            controller.findCustomer(hardcodedFailurePhone);
            fail("Expected DatabaseConnectionFailureException was not thrown.");
        } catch (DatabaseConnectionFailureException exc) {
            assertTrue(exc.getMessage().contains("database"),
                "Exception message should mention the database failure.");
        } catch (CustomerPhoneNumberNotFoundException exc) {
            fail("Wrong exception type - expected DatabaseConnectionFailureException.");
        }
    }

    @Test
    public void testAddDiagnosticResult() {
        RepairOrderDTO order = controller.createRepairOrder("Fix it", "070111", new Bike("GIANT", "SNS", "02"));
        String id = order.getId();

        DiagnosticTaskDTO diag = new DiagnosticTaskDTO("Battery failure", "Have battery problems", new Amount(500),
                "Old battery");
        controller.addDiagnosticResult(id, diag);

        RepairOrderDTO updated = controller.findRepairOrder("070111");
        assertNotNull(updated, "Updated order should exist in registry.");
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, updated.getState(),"State should be READY_FOR_APPROVAL after adding diagnostic result.");
    }

    @Test
    public void testAddRepairTask() {
        RepairOrderDTO order = controller.createRepairOrder("Broken pedal", "070222", new Bike("GIANT", "SNS", "03"));
        RepairTaskDTO task = new RepairTaskDTO("Pedal", "Replace pedal", new Amount(200), RepairTaskState.INCOMPLETE);

        controller.addRepairTask(order.getId(), task);

        RepairOrderDTO updated = controller.findRepairOrder("070222");
        assertNotNull(updated, "Order should be retrievable after adding a task.");
        assertEquals(RepairOrderState.NEWLY_CREATED, updated.getState(),"State should remain NEWLY_CREATED after adding a repair task.");
    }

    @Test
    public void testAcceptRepairOrder() {
        RepairOrderDTO order = controller.createRepairOrder("Squeaky wheel", "070333", new Bike("GIANT", "SNS", "04"));
        controller.acceptRepairOrder(order.getId());

        RepairOrderDTO updatedOrder = controller.findRepairOrder("070333");
        assertTrue(updatedOrder.getState() == RepairOrderState.ACCEPTED, "The order status should be accepted");
    }

    @Test
    public void testRejectAcceptRepairOrder() {
        RepairOrderDTO order = controller.createRepairOrder("Too expensive", "070444", new Bike("GIANT", "SNS", "05"));
        controller.rejectRepairOrder(order.getId());

        RepairOrderDTO updated = controller.findRepairOrder("070444");
        assertTrue(updated.getState() == RepairOrderState.REJECTED, "The order status should be rejected");
    }

    @Test
    public void testFindAllRepairOrders() {
        controller.createRepairOrder("Order 1", "111", new Bike("GIANT", "SNS", "06"));
        controller.createRepairOrder("Order 2", "222", new Bike("GIANT", "SNS", "07"));

        List<RepairOrderDTO> orders = controller.findAllRepairOrders();

        assertNotNull(orders, "The list of orders should not be null.");
        assertTrue(orders.size() >= 2, "The list should contain at least the 2 orders created.");
    }

    @Test
    public void testFindRepairOrderNonExisting() {
        RepairOrderDTO result = controller.findRepairOrder("notExisting");
        assertNull(result);
    }

    @Test
    public void testAcceptRepairOrderAppliesLoyaltyDiscountOnThirdOrder() {
        String phoneNumber = "0701234567";
        controller.createRepairOrder("Order 1", phoneNumber, new Bike("GIANT", "SNS", "11"));
        controller.createRepairOrder("Order 2", phoneNumber, new Bike("GIANT", "SNS", "12"));
        RepairOrderDTO thirdOrder = controller.createRepairOrder("Order 3", phoneNumber, new Bike("GIANT", "SNS", "13"));

        controller.addDiagnosticResult(thirdOrder.getId(),
                new DiagnosticTaskDTO("Battery", "Battery check", new Amount(200), "Worn"));
        controller.addRepairTask(thirdOrder.getId(),
                new RepairTaskDTO("Pads", "Replace pads", new Amount(500), RepairTaskState.INCOMPLETE));

        controller.acceptRepairOrder(thirdOrder.getId());

        RepairOrderDTO updatedThirdOrder = controller.findAllRepairOrders().stream()
                .filter(order -> order.getId().equals(thirdOrder.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(updatedThirdOrder, "The third order should still be stored after acceptance.");
        assertEquals(700, updatedThirdOrder.getTotalCost().getAmount(), "The total cost should include all tasks.");
        assertEquals(630, updatedThirdOrder.getDiscountedTotal().getAmount(),
                "The third order should receive the loyalty discount.");
        assertEquals("10% loyalty discount", updatedThirdOrder.getDiscountDescription(),
                "The DTO should describe the applied strategy.");
    }
}
