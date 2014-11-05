package melichar.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Main model class of the application.
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class Warehouse {

    /* ATTRIBUTES */

    private String product;             // The name of the product which will be added
    private LinkedList<String> data;    // Will contain all the timestamps of when the products where added
    private SimpleDateFormat date;      // Used for creating the timestamps

    /*
     A lock is a tool for controlling access to a shared resource by multiple threads.
     Commonly, a lock provides exclusive access to a shared resource: only one thread at a time
     can acquire the lock and all access to the shared resource requires that the lock be acquired first.

     Conditions provide a means for one thread to suspend execution (to "wait") until notified
     by another thread that some state condition may now be true. A Condition instance is intrinsically bound to a lock
    */
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;

    public Warehouse() {
        product = "";
        data = new LinkedList<String>();
        date = new SimpleDateFormat("YYYY-MM-DD @ hh:mm:ss:SSS");
        lock = new ReentrantLock(true);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    /* METHODS */

    /**
     * The method add() is used for adding a product to the Warehouse.
     * The actual functionality of the method is that the timestamps of when the products
     * are "added" are being noted, not the an actual product.
     * Maximum of products that can be added to the Warehouse is staticly set at 100 products.
     */
    public void add() {
        lock.lock();        // Locks the thread so nothing can be added/removed at this point

        try {
            while (data.size() >= 100) {
                notFull.await();    // Causes the current thread to wait until it is signalled or interrupted.
            }
            data.add(date.format(new Date(System.currentTimeMillis())));    // Creating+Adding the Timestamp
            notEmpty.signal();      // Wakes up one waiting thread
        } catch (InterruptedException e) {
            lock.unlock();          // Unlock the thread so products can be added/removed again
        } finally {
            lock.unlock();          // Unlock the thread so products can be added/removed again
        }
    }

    /**
     * The method delete() is used for removing the product that was last added to the Warehouse
     * It will only do so if there are products in the Warehouse
     */
    public void delete() {
        lock.lock();            // Locks the thread so nothing can be added/removed at this point

        try {
            while (data.size() <= 0) {
                notEmpty.await();   // Causes the current thread to wait until it is signalled or interrupted.
            }
            data.removeLast();      // Remove the last item in the Warehouse (LinkedList)
            notFull.signal();       // Wakes up one waiting thread
        } catch (InterruptedException e) {
            lock.unlock();          // Unlock the thread so products can be added/removed again
        } finally {
            lock.unlock();          // Unlock the thread so products can be added/removed again
        }
    }

    /**
     * Returns a String containing the product (its name), the amount of products in the Warehouse,
     * as well as the timestamps of when they were added to the it
     * @return String with product, amount and timestamps
     */
    public String get() {
        lock.lock();            // Lock the threads so nothing can be added/removed at this point

        try {
            return this.getProduct() + ": " + data.size() + " --- " + data.toString();
        } finally {
            lock.unlock();      // Unlock the thread so products can be added/removed again
        }
    }

    /* GETTER & SETTER */

    /**
     * Returns the product in form of a String
     * @return String containing the product's name
     */
    public String getProduct() {
        return product;
    }

    /**
     * Set the Product (its name) to a certain String
     * @param product The name that shall be given to Product
     */
    public void setProduct(String product) {
        this.product = product;
    }
}
