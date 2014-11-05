package melichar.model;

import melichar.view.Display;

import java.util.Random;

/**
 * The class is responsible for the output of the timestamps
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class StatusThread extends Thread {

    /* ATTRIBUTES */

    private Warehouse lm;
    private Display lv;

    private boolean isRunning;      // Responsible for the run method of this thread

    private boolean TextPane_1;     // Used to determine if there's text in the first text-box
    private boolean TextPane_2;     // Used to determine if there's text in the second text-box
    private boolean TextPane_3;     // Used to determine if there's text in the third text-box
    private boolean TextPane_4;     // Used to determine if there's text in the fourth text-box

    /**
     * Constructor
     * Requires Warehouse object to get the timestamps and a LagerView object to display these
     * @param lm Warehouse Object which includes the timestamps of the products
     * @param lv LagerView Object to display the timestamps
     */
    public StatusThread(Warehouse lm, Display lv) {
        // Setting the Attributes
        this.lm = lm;
        this.lv = lv;
        this.isRunning = true;

        // Use of the totalProducts label on in Display to determine which text-box to write in
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
        while (isRunning) {
            long i = (new Random().nextInt(10)) * 1000;     // Random long which specifies the time between outputs
            try {
                Thread.sleep(i);                            // Puts the thread to sleep for a specific length (long i) and then restarts outputting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Outputs the String from get() method of the Warehouse in the right text-box
            if (TextPane_1) this.lv.setProductTextPane_1Text(this.lv.getProductTextPane_1Text() + '\n' + lm.get() + '\n');
            if (TextPane_2) this.lv.setProductTextPane_2Text(this.lv.getProductTextPane_2Text() + '\n' + lm.get() + '\n');
            if (TextPane_3) this.lv.setProductTextPane_3Text(this.lv.getProductTextPane_3Text() + '\n' + lm.get() + '\n');
            if (TextPane_4) this.lv.setProductTextPane_4Text(this.lv.getProductTextPane_4Text() + '\n' + lm.get() + '\n');
        }
    }

    /* SETTER */

    /**
     * Setter method for the isRunning Attribute which is responsible for the runtime of the thread
     * By setting the Attribute to false, the thread will stop working after finishing its last work cycle.
     * @param isRunning Sets the Attribute to either true or false and thereby stops or starts the thread
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
