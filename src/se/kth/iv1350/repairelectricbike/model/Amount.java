package se.kth.iv1350.repairelectricbike.model;

/**
 * Represents an amount of money. Instances are imumtable.
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

}
