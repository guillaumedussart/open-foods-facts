package org.fantasticfour;

import org.fantasticfour.bll.ProductService;
import org.fantasticfour.bo.Product;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class OpenFoodFacts extends JFrame {
    private JFrame frame;
    private JPanel panelMain;
    private JButton buttonPanel;
    private JTextField textFieldPanel;
    private JTabbedPane tabbedPanePanel;
    private JPanel JPanelTop;
    private JPanel tabbedPaneDescription;
    private JPanel tabbedPaneVitamines;
    private JLabel JLabelTopTitle;
    private JTextArea textArea1;
    private JTextArea textArea2;


    private static final ProductService prodService = ProductService.getSingle();

    public OpenFoodFacts(String title) {
        super(title);
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        buttonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = textFieldPanel.getText();
                List<Product> listProducts = prodService.findByNameLike(food);
                for (Iterator iterator = listProducts.iterator(); iterator.hasNext(); ) {
                    Product product = (Product) iterator.next();
                    textArea2.setText(product.getName());
                }
            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        OpenFoodFacts openFoodFacts = new OpenFoodFacts("Open foods facts");
        openFoodFacts.setVisible(true);
        openFoodFacts.setSize(new Dimension(500, 500));
    }



}
