package melichar.old;

import java.util.Random;

public class StatusThread extends Thread {
    /*Attribute*/
    private Lager l;

    /**
     * Konstruktor
     *
     * @param l das Lager-Objekt
     */
    public StatusThread(Lager l) {
        this.l = l;
    }

    @Override
    public void run() {
        while (true) {
            long i = (new Random().nextInt(10)) * 1000;
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace(); //
            }
            System.out.println(l.get());
        }
    }
}
