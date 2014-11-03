package melichar.control;

import melichar.model.ConsumerThread;
import melichar.model.ProducerThread;
import melichar.model.StatusThread;
import melichar.model.Warehouse;
import melichar.view.LagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class LagerControl implements ActionListener {

    private Warehouse lm;
    private LagerView lv;
    private LinkedHashMap<String,ProducerThread> pt;
    private LinkedHashMap<String,ConsumerThread> ct;
    private LinkedHashMap<String,StatusThread> st;

    public LagerControl() {
        this.lm = new Warehouse();
        this.lv = new LagerView(this);
        pt = new LinkedHashMap<String, ProducerThread>();
        ct = new LinkedHashMap<String, ConsumerThread>();
        st = new LinkedHashMap<String, StatusThread>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* BUTTONS */
        if (e.getSource() == this.lv.getAddButton()) {
            String addedProduct = this.lv.getAddTextFieldText();
            this.lm.setProduct(addedProduct);

            pt.put(addedProduct, new ProducerThread(this.lm));
            ct.put(addedProduct, new ConsumerThread(this.lm));
            st.put(addedProduct, new StatusThread(this.lm, this.lv));

            pt.get(addedProduct).start();
            ct.get(addedProduct).start();
            st.get(addedProduct).start();

            this.lv.totalProductsPlus();
        }

        if (e.getSource() == this.lv.getRemoveButton()) {
            String delete = this.lv.getRemoveTextFieldText();

            if (pt.containsKey(delete)) { pt.get(delete).setRunning(false); }
            if (ct.containsKey(delete)) { ct.get(delete).setRunning(false); }
            if (st.containsKey(delete)) { st.get(delete).setRunning(false); }

            this.lv.totalProductsMinus();
        }

        /* RADIOBUTTONS */
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
