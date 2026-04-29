package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairelectricbike.model.RepairOrder;

public class RepairOrderRegistry {
    /**
     * Internal list of entities
     */
    private final List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     * ~ <<create>> RepairOrderRegistry() that packages the constructor
     */
    RepairOrderRegistry() {
    }

    /**
     * Searches for a repair order by phone number.
     * * @param phoneNumber The unique identifier for the search.
     * 
     * @return The found order as a DTO, or null if no match exists.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber) {
        for (RepairOrder order : repairOrders) {
            if (order.getPhoneNumer().equals(phoneNumber)) {
                return order.toDTO();
            }
        }
        return null;
    }

    /**
     * Finds and retrieves all the repaird orders in the system.
     * 
     * @return A list of DTOs that represent every repair order in the system.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        List<RepairOrderDTO> dtos = new ArrayList<>();

    }
}
