package melichar.control;

import melichar.model.ConsumerThread;
import melichar.model.ProducerThread;
import melichar.model.StatusThread;
import melichar.model.Warehouse;
import melichar.view.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * For lack of a bette name, the Controller Class following the MVC pattern
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class Controller implements ActionListener {

    /* ATTRIBUTES */

    private Warehouse lm;
    private Display lv;

    // The following three LinkedHashMaps are used to create one thread per each product
    private LinkedHashMap<String,ProducerThread> pt;
    private LinkedHashMap<String,ConsumerThread> ct;
    private LinkedHashMap<String,StatusThread> st;

    public Controller() {
        this.lm = new Warehouse();
        this.lv = new Display(this);

        pt = new LinkedHashMap<String, ProducerThread>();
        ct = new LinkedHashMap<String, ConsumerThread>();
        st = new LinkedHashMap<String, StatusThread>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* ADD-BUTTON */
        if (e.getSource() == this.lv.getAddButton()) {
            // Gets the desired name for the product and sets it in the Warehouse
            String addedProduct = this.lv.getAddTextFieldText();
            this.lm.setProduct(addedProduct);

            // Adds the threads, with the product name as key, to the correct LinkedHashMap
            pt.put(addedProduct, new ProducerThread(this.lm));
            ct.put(addedProduct, new ConsumerThread(this.lm));
            st.put(addedProduct, new StatusThread(this.lm, this.lv));

            // Starts each thread of the product
            pt.get(addedProduct).start();
            ct.get(addedProduct).start();
            st.get(addedProduct).start();

            // Total products (in all Warehouses) plus one.
            this.lv.totalProductsPlus();
        }

        /* DELETE-BUTTON */
        if (e.getSource() == this.lv.getRemoveButton()) {
            // Gets String of the product that shall be deleted
            String delete = this.lv.getRemoveTextFieldText();

            // If the LinkedHashMaps contain the String that was input for  delete, all thread will stop. See doc for setRunning().
            if (pt.containsKey(delete)) { pt.get(delete).setRunning(false); }
            if (ct.containsKey(delete)) { ct.get(delete).setRunning(false); }
            if (st.containsKey(delete)) { st.get(delete).setRunning(false); }

            // Total products (in all Warehouses) minus one.
            this.lv.totalProductsMinus();
        }

        /* RADIOBUTTONS */

        /*
            If the event source is one of the radio buttons, both the textfield and the scrollbars will be set invisible.
        */
        if (e.getSource() == this.lv.getProductRadio1()) {
            if (this.lv.getProductRadio1().isSelected()) {
                this.lv.setProductTextPane_1Visibility(true);
            } else {
                this.lv.setProductTextPane_1Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio2()) {
            if (this.lv.getProductRadio2().isSelected()) {
                this.lv.setProductTextPane_2Visibility(true);
            } else {
                this.lv.setProductTextPane_2Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio3()) {
            if (this.lv.getProductRadio3().isSelected()) {
                this.lv.setProductTextPane_3Visibility(true);
            } else {
                this.lv.setProductTextPane_3Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio4()) {
            if (this.lv.getProductRadio4().isSelected()) {
                this.lv.setProductTextPane_4Visibility(true);
            } else {
                this.lv.setProductTextPane_4Visibility(false);
            }
        }
    }
}
