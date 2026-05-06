package se.kth.iv1350.repairelectricbike.model;

/**
 * Represents an amount of money. Instances are immutable.
 */
public final class Amount {
    private final int amount;

    /**
     * Creates a new instance that represents the specified amount.
     * 
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * @return The integer value of this amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of this amount.
     * 
     * @return A string containing the numeric value of this amount.
     */
    @Override
    public String toString() {
        return Integer.toString(amount);
    }
}
