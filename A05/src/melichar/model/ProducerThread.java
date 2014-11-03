package melichar.model;

/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class ProducerThread extends Thread {

    private Warehouse lm;

    public ProducerThread(Warehouse lm) {
        this.lm = lm;
    }

    @Override
    public void run() {
        while (true) {
            lm.add();
        }
    }
}
