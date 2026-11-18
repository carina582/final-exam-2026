package q1;

/**
 * Holds the sales data and performs the report calculations.
 */
public class Product implements IProduct {
    private final String[] names;   // single-dimensional array: product names
    private final int[][] sales;    // two-dimensional array: sales[product][month]

    public Product(String[] names, int[][] sales) {
        this.names = names;
        this.sales = sales;
    }

    // Helper: adds up the months for one product (reused by the methods below to avoid duplicate loops)
    private int productTotal(int productIndex) {
        int sum = 0;
        for (int month = 0; month < sales[productIndex].length; month++) {
            sum += sales[productIndex][month];
        }
        return sum;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        // Add each product's total to get the grand total
        for (int p = 0; p < sales.length; p++) {
            total += productTotal(p);
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        // Count every sales entry (products x months) to divide by
        int entries = 0;
        for (int[] row : sales) {
            entries += row.length;
        }
        // Guard against division by zero when there is no data
        return entries == 0 ? 0 : (double) GetTotalProductsSold() / entries;
    }

    @Override
    public String GetMostPopularProduct() {
        int best = 0;
        // Compare each product's total with the current best
        for (int p = 1; p < sales.length; p++) {
            if (productTotal(p) > productTotal(best)) {
                best = p;
            }
        }
        return names[best] + " (" + productTotal(best) + ")";
    }
}
