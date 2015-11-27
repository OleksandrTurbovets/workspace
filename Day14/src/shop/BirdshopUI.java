package shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;


public class BirdshopUI {


    private JFrame frame;
    private JPanel pTable;
    private JPanel pSelling;
    private JPanel pDeleting;
    private JPanel pPoducts;
    private JPanel pNewPoducts;

    private JTextField tfName;
    private JFormattedTextField tfCount;
    private int productIndex = 0;

    private Quaries quaries;
    private Storage storage;

    public BirdshopUI() {
        quaries = new Quaries();
        storage = new Storage();


        frame = new JFrame("Best Birds Ever");
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLocation(300, 100);

        // create menu
        JMenuItem allPurchases = new JMenuItem("All Purchases");
        allPurchases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });


        JMenuItem buyBirds = new JMenuItem("Buy Birds");
        buyBirds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSellingForm();
            }
        });

        JMenuItem deletePurchase = new JMenuItem("Delete Purchase");
        deletePurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeletingForm();
            }
        });

        JMenuItem allProducts = new JMenuItem("All Products");
        allProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllProducts();
            }
        });

        JMenuItem addNewProduct = new JMenuItem("Add New Product");
        addNewProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddingNewProduct();
            }
        });

        JMenu purchase = new JMenu("Purchase");
        purchase.add(allPurchases);
        purchase.add(buyBirds);
        purchase.add(deletePurchase);

        JMenu products = new JMenu("Products");
        products.add(allProducts);
        products.add(addNewProduct);

        JMenuBar mb = new JMenuBar();
        mb.add(purchase);
        mb.add(products);

        frame.getRootPane().setJMenuBar(mb);

        // create content pane objects
        createSellingPanel();
        createTablePanel();
        createDeletingPurchasePanel();
        createProductsPanel();
        createAddingNewProductPanel();

        frame.getContentPane().add(pTable);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void showData() {
        frame.getContentPane().removeAll();
        createTablePanel(); // TODO make more perfect
        frame.getContentPane().add(pTable);
        frame.pack();
        frame.repaint();
    }

    private void showSellingForm() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pSelling);

        frame.pack();
        frame.repaint();
    }

    private void showDeletingForm() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pDeleting);

        frame.pack();
        frame.repaint();
    }

    private void showAllProducts() {
        frame.getContentPane().removeAll();
        createProductsPanel();
        frame.getContentPane().add(pPoducts);

        frame.pack();
        frame.repaint();
    }

    private void showAddingNewProduct() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pNewPoducts);

        frame.pack();
        frame.repaint();
    }

    private void createAddingNewProductPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lId = new JLabel("Id");
        NumberFormat nfId = NumberFormat.getInstance();
        JFormattedTextField tfId = new JFormattedTextField(nfId);
        tfId.setColumns(2);
        panel.add(lId, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfId, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lName = new JLabel("Name Of Product");
        JTextField tfName = new JTextField();
        tfName.setColumns(25);
        panel.add(lName, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfName, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lPrice = new JLabel("Price");
        NumberFormat nfPrice = NumberFormat.getInstance();
        JFormattedTextField tfPrice = new JFormattedTextField(nfPrice);
        tfPrice.setColumns(5);
        tfPrice.setValue(1);
        panel.add(lPrice, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfPrice, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btnAdd = new JButton("Add");
        panel.add(btnAdd, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(tfId.getText());
                String name = tfName.getText();
                double price = Double.parseDouble(tfPrice.getText());


                try {
                    storage.addNewProduct(id, name, price);

                    showAllProducts();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        pNewPoducts = panel;
    }


    private void createSellingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lName = new JLabel("Your name:");
        tfName = new JTextField();
        tfName.setColumns(25);
        panel.add(lName, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfName, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lProducts = new JLabel("Birds:");
        List<Product> products = quaries.getListOfProducts();
        ButtonGroup productsGroup = new ButtonGroup();

        JPanel pProducts = new JPanel();
        pProducts.setLayout(new GridLayout(products.size(), 0));
        pProducts.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ActionListener rbListener = new RBListener();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            JRadioButton rb = new JRadioButton(p.toString());
            rb.setActionCommand(String.valueOf(i));
            rb.addActionListener(rbListener);
            if (i == 0) {
                rb.setSelected(true);
            }
            productsGroup.add(rb);
            pProducts.add(rb);
        }
        panel.add(lProducts, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(pProducts, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 3, 0, 0), 0, 0));

        JLabel lCount = new JLabel("Count:");
        NumberFormat nf = NumberFormat.getNumberInstance();
        tfCount = new JFormattedTextField(nf);
        tfCount.setColumns(2);
        tfCount.setValue(1);

        panel.add(lCount, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfCount, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btnBuy = new JButton("Buy");
        panel.add(btnBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer();
                c.setName(tfName.getText());


                int count = Integer.parseInt(tfCount.getText());


                try {
                    storage.sell(productIndex, c, count);

                    showData();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        pSelling = panel;
    }

    private void createDeletingPurchasePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel numberOfPurchase = new JLabel("Number of Purchase:");
        NumberFormat nf = NumberFormat.getNumberInstance();
        JFormattedTextField tfNumberOfPurchase = new JFormattedTextField(nf);
        tfNumberOfPurchase.setColumns(2);
        tfNumberOfPurchase.setValue(1);

        panel.add(numberOfPurchase, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfNumberOfPurchase, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btnDelete = new JButton("Delete");
        panel.add(btnDelete, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(tfNumberOfPurchase.getText());

                storage.deletePurchase(number);

                showData();
            }
        });

        pDeleting = panel;
    }

    private void createProductsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        String[] columnNames = {"Id", "Name", "Price"};
        List<Product> products = quaries.getListOfProducts();
        Object[][] data = new Object[products.size()][];
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            Object[] co = new Object[]{p.getId(), p.getName(), p.getPrice()};
            data[i] = co;
        }

        JTable tProducts = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(tProducts);
        panel.add(sp, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        pPoducts = panel;
    }


    private void createTablePanel() {

        pTable = new JPanel();
        pTable.setLayout(new GridBagLayout());


        String[] columnNames = {"TID", "Date", "Customer", "Product", "Count", "Price", "Amount"};
        List<Transaction> transactions = quaries.getListOfTransactions();
        Object[][] data = new Object[transactions.size()][];
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            Object[] co = new Object[]{t.getId(), t.getDate(), t.getCustomer().getName(), t.getProduct().getName(),
                    t.getCount(), t.getPrice(), t.getAmount()};
            data[i] = co;
        }

        JTable tTransactions = new JTable(data, columnNames);
        tTransactions.getColumnModel().getColumn(0).setPreferredWidth(35);
        tTransactions.getColumnModel().getColumn(1).setPreferredWidth(100);

        JScrollPane sp = new JScrollPane(tTransactions);
        pTable.add(sp, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));


        JLabel totalLabel = new JLabel("Total: " + quaries.getTotal().getAmount() + " - " +
                quaries.getTotal().getDiscount() + " = " + quaries.getTotal().getAmountWithDiscount());
        pTable.add(totalLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_END, 0, new Insets(0, 0, 0, 0), 0, 20));

    }

    private class RBListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productIndex = Integer.parseInt(e.getActionCommand());
        }
    }
}
