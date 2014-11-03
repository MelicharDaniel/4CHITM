package melichar.old;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lager, in welches Produkte hinzugefügt werden bzw. gelöscht werden
 *
 * @author Daniel Melichar
 * @version 15.1.2014
 */
public class Lager {

    /* Attribute */
    private String produkt;
    private LinkedList<String> daten;       // Beinhaltet alle Timestamps
    private ReentrantLock lock;             // wird Verwendet um synchronized-Threads zu schließen
    private Condition notFull;              // Überprüft ob der Thread voll ist
    private Condition notEmpty;             // Überprüft ob der Thread leer ist
    private SimpleDateFormat date;          // Für die Ausgabe der Timestamps

    /**
     * Konstruktor
     */
    public Lager() {
        // Initsialisierung der Attribute
        produkt = "";
        daten = new LinkedList<String>();
        date = new SimpleDateFormat("YYYY-MM-DD @ hh:mm:ss:SSS");
        lock = new ReentrantLock(true);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    /**
     * Fügt Produkt(e) ins Lager hinzu
     */
    public void add() {
        // Schließt den Thread
        lock.lock();

        try {
            while (daten.size() >= 100) {
                notFull.await();
            }
            daten.add(date.format(new Date(System.currentTimeMillis())));
            notEmpty.signal();
        } catch (InterruptedException e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }

    /**
     * Löscht Produkt(e) aus dem Lager
     */
    public void delete() {
        lock.lock();

        try {
            while (daten.size() <= 0) {
                notEmpty.await();
            }
            daten.removeLast();
            notFull.signal();
        } catch (InterruptedException e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Wird von StatusThread verwendet für die Consolen-Ausgabe
     *
     * @return die Anzahl, der im Lager vorhandenen Produkte und die Timestamps
     */
    public String get() {
        lock.lock();

        try {
            return this.getProdukt() + ": " + daten.size() + " --- " + daten.toString();
        } finally {
            lock.unlock();
        }
    }

}
