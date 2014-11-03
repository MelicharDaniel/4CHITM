package melichar.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel Melichar
 * @version 03.11.2014
 */
public class Warehouse {

    private String product;
    private LinkedList<String> data;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private SimpleDateFormat date;

    public Warehouse() {
        product = "";
        data = new LinkedList<String>();
        date = new SimpleDateFormat("YYYY-MM-DD @ hh:mm:ss:SSS");
        lock = new ReentrantLock(true);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void add() {
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

    public String get() {
        lock.lock();

        try {
            return this.getProduct() + ": " + data.size() + " --- " + data.toString();
        } finally {
            lock.unlock();
        }
    }

}
