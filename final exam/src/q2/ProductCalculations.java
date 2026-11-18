package q2;

/**
 * Calculates VAT, commission and total for a product price.
 */
public class ProductCalculations implements IProductCalculations {
    // Rates kept as constants so they are defined once and easy to change
    private static final double VAT_RATE = 0.15;
    private static final double COMMISSION_RATE = 0.05;

    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * VAT_RATE;
    }

    @Override
    public double GetCommission() {
        return price * COMMISSION_RATE;
    }

    @Override
    public double GetTotal() {
        // Reuses the other methods so the formulas are not duplicated
        return price + GetVat() + GetCommission();
    }
}
