package se.kth.iv1350.repairelectricbike.integration.exception; 

/**
 * Exception when there is a database connection failure. 
 */

public class DatabaseConnectionFailureException extends Exception {

    /**
     * Creates a new exception that the database connection failed.
     * 
     * @param phoneNumber The phone number linked to the failed connection.
     */
    public DatabaseConnectionFailureException(String phoneNumber) {
        super("Failed to connect to database when trying to find customer with phone number: " + phoneNumber);
    }
}