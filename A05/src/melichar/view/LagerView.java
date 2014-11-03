package melichar.view;

import melichar.control.LagerControl;

import javax.swing.*;

/**
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class LagerView extends JFrame {

    private LagerControl lc;

    private JPanel layoutPanel;
    private JTextField addTextField;
    private JTextField removeTextField;
    private JButton addButton;
    private JButton removeButton;
    private JRadioButton productRadio1;
    private JRadioButton productRadio2;
    private JRadioButton productRadio3;
    private JRadioButton productRadio4;
    private JTextPane productTextPane_1;
    private JTextPane productTextPane_2;
    private JTextPane productTextPane_3;
    private JTextPane productTextPane_4;
    private JScrollPane productScrollPane_1;
    private JScrollPane productScrollPane_2;
    private JScrollPane productScrollPane_3;
    private JScrollPane productScrollPane_4;
    private JLabel totalProducts;

    public LagerView(LagerControl lc) {
        this.lc = lc;
        report();
        init();
    }

    private void report() {
        JOptionPane.showMessageDialog(  null,
                                       "Stand: 29.10.2014 \n" +
                                        "Funktionalität des Removes-Buttons \n" +
                                        "Richtiges aufteilen der Produkte \n" +
                                        "Kommentare \n" +
                                        "\"Spezialisierung\"",
                                        "To-Do / Errors", JOptionPane.PLAIN_MESSAGE);
    }

    private void init() {
        this.setContentPane(this.layoutPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        this.addButton.addActionListener(this.lc);
        this.removeButton.addActionListener(this.lc);
        this.productRadio1.addActionListener(this.lc);
        this.productRadio2.addActionListener(this.lc);
        this.productRadio3.addActionListener(this.lc);
        this.productRadio4.addActionListener(this.lc);
    }

    public int getTotalProducts() {
        return Integer.parseInt(this.totalProducts.getText());
    }

    public void totalProductsPlus() {
        this.totalProducts.setText(Integer.toString(Integer.parseInt(this.totalProducts.getText()) + 1));
    }

    public void totalProductsMinus() {
        this.totalProducts.setText(Integer.toString(Integer.parseInt(this.totalProducts.getText()) - 1));
    }

    public String getProductTextPane_1Text() {
        return productTextPane_1.getText();
    }

    public void setProductTextPane_1Text(String text) {
        this.productTextPane_1.setText(text);
    }

    public String getProductTextPane_2Text() {
        return productTextPane_2.getText();
    }

    public void setProductTextPane_2Text(String text) {
        this.productTextPane_2.setText(text);
    }

    public String getProductTextPane_3Text() {
        return productTextPane_3.getText();
    }

    public void setProductTextPane_3Text(String text) {
        this.productTextPane_3.setText(text);
    }

    public String getProductTextPane_4Text() {
        return productTextPane_4.getText();
    }

    public void setProductTextPane_4Text(String text) {
        this.productTextPane_4.setText(text);
    }

    public String getAddTextFieldText() { return addTextField.getText(); }

    public String getRemoveTextFieldText() {return removeTextField.getText();}

    public void setProductTextPane_1Visibility(boolean vis) {
        this.productTextPane_1.setVisible(vis);
        this.productScrollPane_1.setVisible(vis);
    }

    public void setProductTextPane_2Visibility(boolean vis) {
        this.productTextPane_2.setVisible(vis);
        this.productScrollPane_2.setVisible(vis);
    }

    public void setProductTextPane_3Visibility(boolean vis) {
        this.productTextPane_3.setVisible(vis);
        this.productScrollPane_3.setVisible(vis);
    }

    public void setProductTextPane_4Visibility(boolean vis) {
        this.productTextPane_4.setVisible(vis);
        this.productScrollPane_4.setVisible(vis);
    }

    public JRadioButton getProductRadio1() {
        return productRadio1;
    }

    public JRadioButton getProductRadio2() {
        return productRadio2;
    }

    public JRadioButton getProductRadio3() {
        return productRadio3;
    }

    public JRadioButton getProductRadio4() {
        return productRadio4;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
