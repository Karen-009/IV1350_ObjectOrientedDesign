package se.kth.iv1350.repairelectricbike.model;

/**
 * Defines how a discount is calculated for a repair order total.
 */
public interface DiscountStrategy {
    /**
     * Applies a discount to the specified original amount.
     *
     * @param originalAmount The total cost before discount.
     * @return The discounted total.
     */
    Amount applyDiscount(Amount originalAmount);

    /**
     * @return A human-readable description of the applied discount.
     */
    String getDescription();
}
