package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;

/**
 * Contains all calls to the data store with repair orders.
 * Since there is no database, repair orders are stored
 * directly in this class as a list.
 */
public class RepairOrderRegistry {
    /**
     * Internal list of entities
     */
    private final List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     * Creates a new instance.
     */
    public RepairOrderRegistry() {
    }

    /**
     * Searches for a repair order by phone number.
     * 
     * @param phoneNumber The unique identifier for the search.
     * 
     * @return The found order as a DTO, or null if no match exists.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber) {
        for (RepairOrder order : repairOrders) {
            if (order.getPhoneNumber().equals(phoneNumber)) {
                return order.getRepairOrderDTO();
            }
        }
        return null;
    }

    /**
     * Finds the repair order with the specified ID.
     *
     * @param repairOrderId The ID of the repair order to find.
     * @return The repair order with the specified ID,
     *         or null if no such order exists.
     */
    public RepairOrder findRepairOrderById(String repairOrderId) {
        for (RepairOrder order : repairOrders) {
            if (order.getId().equals(repairOrderId)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Finds and retrieves all the repair orders in the system.
     * 
     * @return A list of DTOs that represent every repair order in the system.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        List<RepairOrderDTO> dtos = new ArrayList<>();
        for (RepairOrder order : repairOrders) {
            dtos.add(order.getRepairOrderDTO());
        }
        return dtos;
    }

    /**
     * Updates an existing repair order in the registry.
     * 
     * @param updatedRepairOrder The DTO containing the updated information.
     */
    public void updateRepairOrder(RepairOrderDTO updateRepairOrder) {
        for (RepairOrder order : repairOrders) {
            if (order.getId().equals(updateRepairOrder.getId())) {
                order.updateState(updateRepairOrder);
                return;
            }
        }
    }

    /**
     * Saves a new repair order to the registry.
     * 
     * @param repairOrder The repair order to be saved.
     */
    public void saveRepairOrder(RepairOrder repairOrder) {
        repairOrders.add(repairOrder);
    }
}