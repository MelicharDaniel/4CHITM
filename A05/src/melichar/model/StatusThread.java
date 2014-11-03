package melichar.model;

import melichar.view.LagerView;

import java.util.Random;

/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class StatusThread extends Thread {

    private Warehouse lm;
    private LagerView lv;
    private boolean isRunning;
    private boolean TextPane_1;
    private boolean TextPane_2;
    private boolean TextPane_3;
    private boolean TextPane_4;

    public StatusThread(Warehouse lm, LagerView lv) {
        this.lm = lm;
        this.lv = lv;
        this.isRunning = true;

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

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
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
