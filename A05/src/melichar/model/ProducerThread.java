package melichar.model;

/**
 * The thread which will constantly add products to the Warehouse
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ProducerThread extends Thread {

    /* ATTRIBUTES */
    private Warehouse lm;
    private boolean isRunning;

    public ProducerThread(Warehouse lm) {
        this.lm = lm;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {         // Endless loop, so that products are always deleted
            lm.add();               // Usage of the add() method of Warehouse
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
