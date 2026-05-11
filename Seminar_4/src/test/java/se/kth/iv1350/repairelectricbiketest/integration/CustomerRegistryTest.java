package se.kth.iv1350.repairelectricbiketest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

import se.kth.iv1350.repairelectricbike.integration.CustomerRegistry;
import se.kth.iv1350.repairelectricbike.integration.DatabaseConnectionFailureException;
import se.kth.iv1350.repairelectricbike.integration.CustomerDTO;
import se.kth.iv1350.repairelectricbike.integration.CustomerPhoneNumberNotFoundException;

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
    public void testFindCustomerExisting() throws CustomerPhoneNumberNotFoundException {
        String existingPhone = "0701234567";
        String expectedName = "Alice Andersson";

        CustomerDTO result = registry.findCustomer(existingPhone);

        assertNotNull(result, "Should find a customer that exists in the registry.");
        assertEquals(expectedName, result.getName(), "The retrieved customer name does not match.");
    }

    @Test
    public void testFindingCustomerNonExisting() {
        String nonExistingPhone = "0000000000";

        try {
            registry.findCustomer(nonExistingPhone);
            fail("A customer with a non existing phone number was found.");
        } catch (CustomerPhoneNumberNotFoundException exc) {
            assertTrue(exc.getMessage().contains(nonExistingPhone), 
            "Exception message should contain the phone number that was searched.");
            assertEquals(nonExistingPhone, exc.getPhoneNumber(), "Exception should store the number that was not found.");
        }
    }

    @Test
    public void testDatabaseFailureThrowsException() {
        String hardcodedFailurePhone = "0000000001";

        try {
            registry.findCustomer(hardcodedFailurePhone);
            fail("Expected CustomerRegistryException was not thrown.");
        } catch (DatabaseConnectionFailureException exc) {
            assertTrue(
                exc.getMessage().contains("database"),
                "Exception message should mention the database failure.");
        } catch (CustomerPhoneNumberNotFoundException exc) {
            fail("Wrong exception type thrown - expected CustomerRegistryException.");
        }
    }
}
