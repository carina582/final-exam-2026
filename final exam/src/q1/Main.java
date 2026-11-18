package q1;

import java.util.Scanner;

/**
 * Console entry point: captures sales for 3 products over 3 months and prints the report.
 */
public class Main {
    private static final int MONTHS = 3;

    public static void main(String[] args) {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = new int[names.length][MONTHS];
        Scanner in = new Scanner(System.in);

        // Capture monthly sales for every product
        for (int p = 0; p < names.length; p++) {
            for (int m = 0; m < MONTHS; m++) {
                System.out.print("Enter number of sales for month " + (m + 1)
                        + " for " + names[p] + " mattress: ");
                sales[p][m] = readWholeNumber(in);
            }
            System.out.println();
        }

        // Print the report using the PrintProduct class
        PrintProduct printer = new PrintProduct(new Product(names, sales));
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("*".repeat(50));
        printer.printTotalProductSales();
        printer.printAverageProductSales();
        printer.printMostPopularProduct();
    }

    // Keeps asking until the user enters a non-negative whole number
    private static int readWholeNumber(Scanner in) {
        while (true) {
            try {
                int value = Integer.parseInt(in.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException ex) {
                // fall through and ask again
            }
            System.out.print("Invalid number, try again: ");
        }
    }
}
