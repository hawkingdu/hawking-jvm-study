package edu.hawking.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 杜皓君 created by 2021/5/13
 * ObjectDeadLockDemo
 **/
public class ObjectDeadLockDemo {

    public static void main(String[] args) {
        final Object lockObj1 = new Object();
        final Object lockObj2 = new Object();
        Thread t1 = new Thread(()->{
            synchronized (lockObj1) {
                System.out.println(Thread.currentThread().getName()+" 获取 lockObj1 锁成功------执行业务逻辑");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (lockObj2) {
                        System.out.println(Thread.currentThread().getName()+" 获取 lockObj2 锁成功------执行业务逻辑");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1");

        Thread t2= new Thread(()->{
            synchronized (lockObj1) {
                System.out.println(Thread.currentThread().getName()+" 获取 lockObj2 锁成功------执行业务逻辑");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (lockObj2) {
                        System.out.println(Thread.currentThread().getName()+" 获取 lockObj1 锁成功------执行业务逻辑");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main 程序执行完成。");
    }
}
