package edu.hawking.collections;

import java.util.LinkedList;

/**
 * 杜皓君 created by 2021/6/20
 * MyBlockingQueue
 **/
public class MyBlockingQueue<E> {

    private LinkedList<E> queue = new LinkedList<>();
    private volatile int size;

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void push(E e) {
        synchronized (queue) {
            while (queue.size() >= size) {
                try {
                    queue.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            queue.addLast(e);
            queue.notifyAll();
        }
    }

    public E pop() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            E e = queue.pollFirst();
            queue.notifyAll();
            return e;
        }
    }

    public int size() {
        synchronized (queue) {
            return queue.size();
        }
    }

}
