package q1;

/**
 * Responsible only for printing the report data (Q1.3).
 */
public class PrintProduct {
    private final IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    // Prints the grand total of products sold
    public void printTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    // Prints the average, rounded to a whole number like the sample output
    public void printAverageProductSales() {
        System.out.printf("AVERAGE PRODUCT SALES: %.0f%n", product.GetAverageProductSold());
    }

    // Prints the best-selling product and its total
    public void printMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }
}
