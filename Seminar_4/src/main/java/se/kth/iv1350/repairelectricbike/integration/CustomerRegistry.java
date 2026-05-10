package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with customers.
 * Since there is no database, customers are stored
 * directly in this class as hardcoded data.
 */

public class CustomerRegistry {

    private static final String DATABASE_CONNECTION_FAILURE_PHONE_NUMBER = "0700000000";
    private List<CustomerDTO> customers = new ArrayList<>();

    /**
     * Creates a new instance and populates it with
     * hardcoded customer data for testing purposes.
     */
    public CustomerRegistry() {
        addCustomers();
    }

    /**
     * Finds the customer with the specified phone number.
     * 
     * @param phoneNumber The phone number used to identify the customer.
     * @return A DTO containing the customer information.
     * @throws CustomerPhoneNumberNotFoundException If no customer is found with the specified phone number.
     * @throws DatabaseConnectionFailureException If there is a failure in connection to the database.
     */
    public CustomerDTO findCustomer(String phoneNumber) throws CustomerPhoneNumberNotFoundException {

        if (DATABASE_CONNECTION_FAILURE_PHONE_NUMBER.equals(phoneNumber)) {
                throw new DatabaseConnectionFailureException("Failed to contact the customer database.", 
                new Exception("Database connection failure"));
        }

        for (CustomerDTO customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        
        throw new CustomerPhoneNumberNotFoundException(phoneNumber);
    }

    private void addCustomers() {
        customers.add(new CustomerDTO(
                "Alice Andersson",
                "alice@email.com",
                "Trek",
                "FX3",
                "SN123456",
                "0701234567"));
        customers.add(new CustomerDTO(
                "Bob Bengtsson",
                "bob@email.com",
                "Giant",
                "Escape",
                "SN654321",
                "0709876543"));
    }
}
