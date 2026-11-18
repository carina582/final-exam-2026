package q1;

/**
 * Contract for the sales calculations required by the product report (Q1.2).
 */
public interface IProduct {
    int GetTotalProductsSold();          // total units sold across all products and months
    double GetAverageProductSold();      // average units sold per product per month
    String GetMostPopularProduct();      // product name with the highest total sales
}
