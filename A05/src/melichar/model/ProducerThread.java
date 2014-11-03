package melichar.model;

/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ProducerThread extends Thread {

    private Warehouse lm;
    private boolean isRunning;

    public ProducerThread(Warehouse lm) {
        this.lm = lm;
        this.isRunning = true;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        while (isRunning) {
            lm.add();
        }
    }
}
