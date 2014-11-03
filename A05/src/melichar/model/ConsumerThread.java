package melichar.model;


/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ConsumerThread extends Thread {

    private Warehouse lm;


    public ConsumerThread(Warehouse lm) {
        this.lm = lm;
    }

    public void run() {
        while (true) {
            lm.delete();
        }
    }

}
