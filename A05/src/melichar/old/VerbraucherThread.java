package melichar.old;

/**
 * Der Verbraucher Thread der Produkte vom Lager raus nimmt
 *
 * @author Daniel Melichar
 * @version 15.10.2014
 */
public class VerbraucherThread extends Thread {

    /* Attribute */
    private Lager l;

    /**
     * Konstruktor
     *
     * @param l ein Lager-Objekt
     */
    public VerbraucherThread(Lager l) {
        this.l = l;
    }

    @Override
    public void run() {
        // Führt eine Endlosschleife aus
        while (true) {
            // Löscht Produkt(e) aus dem Lager
            l.delete();
        }
    }
}
