package q2;

/**
 * Contract for the sale calculations required by Q2.5.
 */
public interface IProductCalculations {
    double GetVat();         // 15% of the price
    double GetCommission();  // 5% of the price
    double GetTotal();       // price + VAT + commission
}
