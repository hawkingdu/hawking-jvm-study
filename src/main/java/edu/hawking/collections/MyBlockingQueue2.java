package edu.hawking.collections;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 杜皓君 created by 2021/6/20
 * MyBlockingQueue
 **/
public class MyBlockingQueue2<E> {

    private LinkedList<E> queue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();
    private volatile int size;

    public MyBlockingQueue2(int size) {
        this.size = size;
    }

    public void push(E e) {
        lock.lock();
        try {
            while (queue.size() >= size) {
                try {
                    fullCondition.await();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            queue.addLast(e);
            emptyCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    public E pop() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            E e = queue.pollFirst();
            fullCondition.signal();
            return e;
        } finally {
            lock.unlock();
        }

    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }

    }

}
