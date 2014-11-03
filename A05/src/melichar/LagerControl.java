package melichar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class LagerControl implements ActionListener {

    private LagerModel[] lm;
    private LagerView lv;

    public LagerControl() {
        this.lm = new LagerModel[5];
        for (int x = 0; x < 5; x++) {
            lm[x] = new LagerModel();
        }

        this.lv = new LagerView(this.lm[0], this);
    }

    class ErzeugerThread extends Thread {
        private LagerModel lm;

        /**
         * Konstruktor
         *
         * @param lm das lager-Object in welches etwas hinzugefügt werden soll
         */
        public ErzeugerThread(LagerModel lm) {
            this.lm = lm;
        }

        @Override
        public void run() {
            // Führt eine Endlosschleife aus
            while (true) {
                // Fügt product(e) ins Lager hinzu
                lm.add();
            }
        }
    }

    class VerbraucherThread extends Thread {

        /* Attribute */
        private LagerModel lm;

        /**
         * Konstruktor
         *
         * @param lm ein Lager-Objekt
         */
        public VerbraucherThread(LagerModel lm) {
            this.lm = lm;
        }

        @Override
        public void run() {
            // Führt eine Endlosschleife aus
            while (true) {
                // Löscht product(e) aus dem Lager
                lm.delete();
            }
        }
    }

    class StatusThread extends Thread {
        /*Attribute*/
        private LagerModel lm;
        private LagerView lv;
        private boolean TextPane_1;
        private boolean TextPane_2;
        private boolean TextPane_3;
        private boolean TextPane_4;

        /**
         * Konstruktor
         *
         * @param lm das Lager-Objekt
         */
        public StatusThread(LagerModel lm, LagerView lv) {
            this.lm = lm;
            this.lv = lv;

            switch (this.lv.getTotalProducts()) {
                case 0:
                case 1:
                    TextPane_1 = true;
                    TextPane_2 = false;
                    TextPane_3 = false;
                    TextPane_4 = false;
                    break;
                case 2:
                    TextPane_1 = true;
                    TextPane_2 = true;
                    TextPane_3 = false;
                    TextPane_4 = false;
                    break;
                case 3:
                    TextPane_1 = true;
                    TextPane_2 = true;
                    TextPane_3 = true;
                    TextPane_4 = false;
                    break;
                case 4:
                    TextPane_1 = true;
                    TextPane_2 = true;
                    TextPane_3 = true;
                    TextPane_4 = true;
            }
        }

        @Override
        public void run() {
            while (true) {
                long i = (new Random().nextInt(10)) * 1000;
                try {
                    Thread.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (TextPane_1) this.lv.setProductTextPane_1Text(this.lv.getProductTextPane_1Text() + '\n' + lm.get());
                if (TextPane_2) this.lv.setProductTextPane_2Text(this.lv.getProductTextPane_2Text() + '\n' + lm.get());
                if (TextPane_3) this.lv.setProductTextPane_3Text(this.lv.getProductTextPane_3Text() + '\n' + lm.get());
                if (TextPane_4) this.lv.setProductTextPane_4Text(this.lv.getProductTextPane_4Text() + '\n' + lm.get());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* BUTTONS */
        if (e.getSource() == this.lv.getAddButton()) {
            int x = this.lv.getTotalProducts();

            this.lv.totalProductsPlus();
            new ErzeugerThread(this.lm[x + 1]).start();
            new VerbraucherThread(this.lm[x + 1]).start();
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
