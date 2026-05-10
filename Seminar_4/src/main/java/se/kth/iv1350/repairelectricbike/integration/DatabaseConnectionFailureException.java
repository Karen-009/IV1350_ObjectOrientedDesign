package se.kth.iv1350.repairelectricbike.integration;

/**
 * Exception when there is a database connection failure. 
 */

public class DatabaseConnectionFailureException extends RuntimeException {

    /**
     * Creates a new instance with a message describing what went wrong.
     * 
     * @param msg   A message describing the failure.
     * @param cause The original exception that caused this failure.
     */
    public DatabaseConnectionFailureException(String msg, Exception cause) {
        super(msg, cause);
    }
}