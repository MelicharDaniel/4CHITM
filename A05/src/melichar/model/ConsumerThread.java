package melichar.model;


/**
 * The Thread that will constantly delete products to the Warehouse
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ConsumerThread extends Thread {

    /* ATTRIBUTES */
    private Warehouse lm;
    private boolean isRunning;      // Responsible for the run method of this thread

    public ConsumerThread(Warehouse lm) {
        this.lm = lm;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {         // Endless loop, so that products are always deleted
            lm.delete();            // Usage of the delete() method of Warehouse
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
