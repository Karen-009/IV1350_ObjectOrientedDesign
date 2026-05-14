package se.kth.iv1350.repairelectricbike.model;

/**
 * Applies a loyalty discount to the repair order total.
 */
public class LoyaltyDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10;

    /**
     * Applies the loyalty discount to the specified amount.
     *
     * @param originalAmount The total cost before discount.
     * @return The discounted total cost.
     */
    @Override
    public Amount applyDiscount(Amount originalAmount) {
        int discounted = (int) Math.round(originalAmount.getAmount() * (1 - DISCOUNT_RATE));
        return new Amount(discounted);
    }

    /**
     * @return A description of the applied loyalty discount.
     */
    @Override
    public String getDescription() {
        return "10% loyalty discount";
    }
}
