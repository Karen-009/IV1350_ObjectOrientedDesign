package IV1350_ObjectOrientedDesign.tests.se.kth.iv1350.repairelectricbiketest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderDTO;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderState;
import se.kth.iv1350.repairelectricbike.model.RepairOrder;
import se.kth.iv1350.repairelectricbike.model.Bike;

import java.util.List;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairOrder testRepairOrder;
    private String phoneNumber = "0701112233";

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        Bike bike = new Bike("BIKE", "X123", "123");
        testRepairOrder = new RepairOrder("Flat tire", phoneNumber, bike);
        registry.saveRepairOrder(testRepairOrder);
    }

    @AfterEach
    public void tearDown() {
        registry = null;
        testRepairOrder = null;
    }

    @Test
    public void testSaveAndFindById() {
        String id = testRepairOrder.getId();
        RepairOrder found = registry.findRepairOrderById(id);

        assertNotNull(found, "Should find the order by its unique ID.");
        assertEquals(id, found.getId(), "The found order ID should match the saved one.");
    }

    @Test
    public void testFindRepairOrderMissingId() {
        RepairOrder found = registry.findRepairOrderById("non-existent-id");

        assertNull(found, "Should return null for an ID that doesn't exist.");
    }

    @Test
    public void testFindRepairOrderByPhone() {
        RepairOrderDTO foundDto = registry.findRepairOrder(phoneNumber);

        assertNotNull(foundDto, "Should find the order DTO by phone number.");
        assertEquals(phoneNumber, testRepairOrder.getPhoneNumber(), "The phone number in the found DTO should match.");
    }

    @Test
    public void testFindAllRepairOrders() {
        List<RepairOrderDTO> allOrders = registry.findAllRepairOrders();

        assertEquals(1, allOrders.size(), "Registry should contain exactly 1 order.");
        assertEquals(testRepairOrder.getId(), allOrders.get(0).getId(), "The DTO in the list should match our saved order.");
    }

    @Test
    public void testUpdateRepairOrder() {
        // Create a DTO with the same ID but a new state
        RepairOrderDTO updatedDto = new RepairOrderDTO(
                testRepairOrder.getId(),
                "2024-01-01",
                "Desc",
                "2024-01-02",
                RepairOrderState.ACCEPTED);

        registry.updateRepairOrder(updatedDto);

        RepairOrder found = registry.findRepairOrderById(testRepairOrder.getId());
        assertEquals(RepairOrderState.ACCEPTED, found.getRepairOrderDTO().getState(),
                "The state in the registry should have been updated.");
    }
}
