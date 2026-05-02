package se.kth.iv1350.repairelectricbiketest.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.RegistryCreator;
import se.kth.iv1350.repairelectricbike.integration.RepairOrderRegistry;

import static org.junit.jupiter.api.Assertions.*;

public class RegistryCreatorTest {
    private RegistryCreator creator;

    @BeforeEach
    public void setUp() {
        creator = new RegistryCreator();
    }

    @AfterEach
    public void tearDown() {
        creator = null;
    }

    @Test
    public void testGetCustomerRegistry() {
        CustomerRegistry result = creator.getCustomerRegistry();
        assertNotNull(result, "CustomerRegistry should be instantiated by RegistryCreator.");
    }

    @Test
    public void testGetRepairOrderRegistry() {
        RepairOrderRegistry result = creator.getRepairOrderRegistry();
        assertNotNull(result, "RepairOrderRegistry should be instantiated by RegistryCreator.");
    }
}
