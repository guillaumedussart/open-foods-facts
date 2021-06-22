package org.fantasticfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public OpenFoodFacts(String title) {
        super(title);
        createUIComponents();
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        buttonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food = textFieldPanel.getText();
                JOptionPane.showMessageDialog(null, food);
            }
        });
    }

    public static void main(String[] args) {
        OpenFoodFacts openFoodFacts = new OpenFoodFacts("Test");
        openFoodFacts.setVisible(true);
        openFoodFacts.setSize(new Dimension(500, 500));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelMain = new JPanel();
        panelMain.add(buttonPanel);
        panelMain.add(textFieldPanel);
        panelMain.add(tabbedPanePanel);
        panelMain.add(JPanelTop);
        panelMain.add(tabbedPaneDescription);
        panelMain.add(tabbedPaneVitamines);
        panelMain.add(JLabelTopTitle);
    }


}
