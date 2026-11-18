package q2;

import javax.swing.*;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Swing GUI for capturing and processing a mattress sale (Q2.1 - Q2.4).
 */
public class ProductApp extends JFrame {
    private static final String DATA_FILE = "data.txt";

    private final JTextField txtName = new JTextField(20);
    private final JComboBox<String> cboProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 8, 8));

        // Menu bar: File > Exit ; Tools > Process, Clear (Q2.4)
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);
        JMenu menuTools = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");
        menuTools.add(itemProcess);
        menuTools.add(itemClear);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);

        // Form controls (Q2.2)
        add(new JLabel("Customer Name:")); add(txtName);
        add(new JLabel("Select Product:")); add(cboProduct);
        add(new JLabel("Enter Price:"));    add(txtPrice);
        add(new JLabel(""));                add(btnProcess);

        // The button and the Process menu item share one method, so the logic is not duplicated
        btnProcess.addActionListener(e -> processSale());
        itemProcess.addActionListener(e -> processSale());
        itemClear.addActionListener(e -> clearForm());
        itemExit.addActionListener(e -> System.exit(0));

        setSize(450, 250);
        setLocationRelativeTo(null);
    }

    // Resets the form: empty text boxes and first product selected
    private void clearForm() {
        txtName.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
    }

    // Validates input, calculates amounts, shows them and saves them to data.txt (Q2.3)
    private void processSale() {
        String name = txtName.getText().trim();
        String product = (String) cboProduct.getSelectedItem();

        if (name.isEmpty()) {
            showError("Please enter the customer name.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            showError("Please enter a valid price.");
            return;
        }
        if (price < 0) {
            showError("The price cannot be negative.");
            return;
        }

        // Calculations are delegated to ProductCalculations
        IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        // Build the text once and reuse it for the dialog and the file
        String details = "Customer Name: " + name + "\n"
                + "Product: " + product + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total;

        JOptionPane.showMessageDialog(this, details, "Message", JOptionPane.INFORMATION_MESSAGE);
        saveToFile(details);
    }

    // Appends one record to data.txt in the format shown in the brief
    private void saveToFile(String details) {
        try (PrintWriter out = new PrintWriter(new FileWriter(DATA_FILE, true))) {
            out.println("DATA LOG");
            out.println("*".repeat(40));
            out.println(details);
            out.println("*".repeat(40));
            out.println();
        } catch (IOException ex) {
            showError("Could not write to " + DATA_FILE);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Swing components must be created on the event dispatch thread
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
