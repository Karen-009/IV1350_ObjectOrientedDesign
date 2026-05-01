package se.kth.iv1350.repairelectricbike.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with customers.
 * Since there is no database, customers are stored
 * directly in this class as hardcoded data.
 */

public class CustomerRegistry {
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
     * @return A DTO containing the customer information, or null if no customer is
     *         found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        for (CustomerDTO customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
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
