package se.kth.iv1350.repairelectricbike.integration;

/**
 * This class is responsible for instantiating all registries
 */

public class RegistryCreator {
    private CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
    private RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

    /**
     * Get the value of customerRegistry
     * 
     * @return the value of customerRegistry
     */
    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    /**
     * Get the value of repairOrderRegistry
     * 
     * @return the value of repairOrderRegistry
     */
    public RepairOrderRegistry getRepairOrderRegistry() {
        return repairOrderRegistry;
    }

}
