package org.fantasticfour;

import org.fantasticfour.bll.ProductService;
import org.fantasticfour.bo.Product;
import org.fantasticfour.bo.Vitamine;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

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
    private JTable table1;
    private JList list1;
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
                List<Product> listProducts = null;
                try {
                    listProducts = prodService.findByNameLike(food);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NotFindProductException notFindProductException) {
                    notFindProductException.printStackTrace();
                } catch (NotFindManagerException notFindManagerException) {
                    notFindManagerException.printStackTrace();
                }
                for (Iterator iterator = listProducts.iterator(); iterator.hasNext(); ) {
                    Product product = (Product) iterator.next();


                    Set<Vitamine> vitamineList =  product.getVitamines();
                    Iterator<Vitamine> iterator2 = vitamineList.iterator();
                    while (iterator2.hasNext()) {
                        Entry<String, Vitamine> entry =  iterator2.next();
                        System.out.println(entry.getKey() + " ==> " + entry.getValue());
                    }
                    for (int i = 0; i < vitamineList.size(); i++) {
                        String vitamine = vitamineList.toString();
                        textArea2.setText("Vitamines (apport pour 100g) : " + vitamine);
                    }
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
