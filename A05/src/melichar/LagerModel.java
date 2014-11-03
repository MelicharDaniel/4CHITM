package melichar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel Melichar
 * @version 29.10.2014
 */
public class LagerModel {

    /* Attribute */
    private String product;
    private LinkedList<String> data;       // Beinhaltet alle Timestamps
    private ReentrantLock lock;             // wird Verwendet um synchronized-Threads zu schließen
    private Condition notFull;              // Überprüft ob der Thread voll ist
    private Condition notEmpty;             // Überprüft ob der Thread leer ist
    private SimpleDateFormat date;          // Für die Ausgabe der Timestamps

    public LagerModel() {
        // Initsialisierung der Attribute
        product = "";
        data = new LinkedList<String>();
        date = new SimpleDateFormat("YYYY-MM-DD @ hh:mm:ss:SSS");
        lock = new ReentrantLock(true);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    /**
     * Fügt product(e) ins Lager hinzu
     */
    public void add() {
        // Schließt den Thread
        lock.lock();

        try {
            while (data.size() >= 100) {
                notFull.await();
            }
            data.add(date.format(new Date(System.currentTimeMillis())));
            notEmpty.signal();
        } catch (InterruptedException e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Löscht product(e) aus dem Lager
     */
    public void delete() {
        lock.lock();

        try {
            while (data.size() <= 0) {
                notEmpty.await();
            }
            data.removeLast();
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
     * @return die Anzahl, der im Lager vorhandenen producte und die Timestamps
     */
    public String get() {
        lock.lock();

        try {
            return this.getProduct() + ": " + data.size() + " --- " + data.toString();
        } finally {
            lock.unlock();
        }
    }
}
