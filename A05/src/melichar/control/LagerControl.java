package melichar.control;

import melichar.model.ConsumerThread;
import melichar.model.ProducerThread;
import melichar.model.StatusThread;
import melichar.model.Warehouse;
import melichar.view.LagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class LagerControl implements ActionListener {

    private Warehouse[] lm;
    private LagerView lv;

    public LagerControl() {
        this.lm = new Warehouse[5];
        for (int x = 0; x < 5; x++) {
            lm[x] = new Warehouse();
        }

        this.lv = new LagerView(this.lm[0], this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* BUTTONS */
        if (e.getSource() == this.lv.getAddButton()) {
            int x = this.lv.getTotalProducts();

            this.lv.totalProductsPlus();
            new ProducerThread(this.lm[x + 1]).start();
            new ConsumerThread(this.lm[x + 1]).start();
            new StatusThread(this.lm[x + 1], this.lv).start();

            this.lm[x + 1].setProduct(this.lv.getAddTextFieldText());
        }

        if (e.getSource() == this.lv.getRemoveButton()) {
            // TODO
        }

        /* RADIOBUTTONS */
        if (e.getSource() == this.lv.getProductRadio1()) {
            if (this.lv.getProductRadio1().isSelected()) {
                // Wenn es jetzt selected wurde
                this.lv.setProductTextPane_1Visibility(true);
            } else {
                this.lv.setProductTextPane_1Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio2()) {
            if (this.lv.getProductRadio2().isSelected()) {
                // Wenn es jetzt selected wurde
                this.lv.setProductTextPane_2Visibility(true);
            } else {
                this.lv.setProductTextPane_2Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio3()) {
            if (this.lv.getProductRadio3().isSelected()) {
                // Wenn es jetzt selected wurde
                this.lv.setProductTextPane_3Visibility(true);
            } else {
                this.lv.setProductTextPane_3Visibility(false);
            }
        }
        if (e.getSource() == this.lv.getProductRadio4()) {
            if (this.lv.getProductRadio4().isSelected()) {
                // Wenn es jetzt selected wurde
                this.lv.setProductTextPane_4Visibility(true);
            } else {
                this.lv.setProductTextPane_4Visibility(false);
            }
        }
    }
}
