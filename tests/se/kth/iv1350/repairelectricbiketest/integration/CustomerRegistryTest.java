package IV1350_ObjectOrientedDesign.tests.se.kth.iv1350.repairelectricbiketest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.CustomerDTO;

public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    @AfterEach
    public void tearDown() {
        registry = null;
    }

    @Test
    public void testFindCustomerExisting() {
        String existingPhone = "0701234567";
        String expectedName = "Alice Andersson";

        CustomerDTO result = registry.findCustomer(existingPhone);

        assertNotNull(result, "Should find a customer that exists in the registry.");
        assertEquals(expectedName, result.getName(), "The retrieved customer name does not match.");
    }

    @Test
    public void testFindingCustomerNonExisting() {
        String nonExistingPhone = "0000000000";

        CustomerDTO result = registry.findCustomer(nonExistingPhone);

        assertNull(result, "Should return null when searching for a non-existing phone number.");
    }

}
