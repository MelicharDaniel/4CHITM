package melichar.model;


/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ConsumerThread extends Thread {

    private Warehouse lm;
    private boolean isRunning;

    public ConsumerThread(Warehouse lm) {
        this.lm = lm;
        this.isRunning = true;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            lm.delete();
        }
    }

}
