package se.kth.iv1350.repairelectricbike.model;

/**
 * Applies no discount to the repair order total.
 */
public class NoDiscountStrategy implements DiscountStrategy {
    /**
     * Returns the original amount unchanged.
     *
     * @param originalAmount The original total cost.
     * @return The unchanged total cost.
     */
    @Override
    public Amount applyDiscount(Amount originalAmount) {
        return originalAmount;
    }

    /**
     * @return A description stating that no discount was applied.
     */
    @Override
    public String getDescription() {
        return "No discount";
    }
}
