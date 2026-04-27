package se.kth.iv1350.repairelectricbike.integration;

/**
 * This class is responsible for instantiating all registries
 */

public class RegistryCreator {
    private CustomerRegistery customerRegistery = new CustomerRegistery();

    /**
     * Get the value of customerRegistry
     * 
     * @return the value of customerRegistry
     */
    public CustomerRegistery getCustomerRegistry() {
        return customerRegistery;
    }

    private RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

    /**
     * Get the value of repairOrderRegistry
     * 
     * @return the value of customerRegistry
     */
    public RepairOrderRegistry getRepairOrderRegistry() {
        return repairOrderRegistry;
    }

}
