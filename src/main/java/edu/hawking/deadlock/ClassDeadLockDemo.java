package edu.hawking.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 杜皓君 created by 2021/5/13
 * DeadLockDemo
 * class锁 死锁
 **/
public class ClassDeadLockDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            synchronized (Integer.class) {
                System.out.println(Thread.currentThread().getName()+" 获取 Integer 锁成功------执行业务逻辑");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (String.class) {
                        System.out.println(Thread.currentThread().getName()+" 获取 String 锁成功------执行业务逻辑");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1");

        Thread t2 = new Thread(()->{
            synchronized (Integer.class) {
                System.out.println(Thread.currentThread().getName()+" 获取 Integer 锁成功------执行业务逻辑");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (String.class) {
                        System.out.println(Thread.currentThread().getName()+" 获取 String 锁成功------执行业务逻辑");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程2");

        t1.start();
        t2.start();
        System.out.println("Main 程序执行完成。");
    }
}
