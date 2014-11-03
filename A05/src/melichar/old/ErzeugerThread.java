package melichar.old;

/**
 * Der Erzeuger Thread, welcher Produkte ins Lager stellt
 *
 * @author Daniel Melichar
 * @version 15.10.2014
 */
public class ErzeugerThread extends Thread {
    private Lager l;

    /**
     * Konstruktor
     *
     * @param l das lager-Object in welches etwas hinzugefügt werden soll
     */
    public ErzeugerThread(Lager l) {
        this.l = l;
    }

    @Override
    public void run() {
        // Führt eine Endlosschleife aus
        while (true) {
            // Fügt Produkt(e) ins Lager hinzu
            l.add();
        }
    }
}
