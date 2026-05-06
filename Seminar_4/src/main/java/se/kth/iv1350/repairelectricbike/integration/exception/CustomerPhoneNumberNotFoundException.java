package se.kth.iv1350.repairelectricbike.integration.exception; 

/**
 * Exception when a customer with a specific phone number could not be found. 
 */

public class CustomerPhoneNumberNotFoundException extends Exception {

    /**
     * Creates a new exception that phone number was not found
     * 
     * @param phoneNumber The phone number that was not found.
     */
    public CustomerPhoneNumberNotFoundException(String phoneNumber) {
        super("No customer could be found with the phone number: " + phoneNumber);
    }
}