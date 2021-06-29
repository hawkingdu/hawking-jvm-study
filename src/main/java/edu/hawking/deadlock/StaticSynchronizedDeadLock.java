package edu.hawking.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 杜皓君 created by 2021/5/13
 * StaticSynchronizedDeadLock
 **/
public class StaticSynchronizedDeadLock {
    public static void main(String[] args) {
        final Object lockObj1 = new Object();
        final Object lockObj2 = new Object();
        Thread t1 = new Thread(()->{
            Test.method1();
            Test.method2();

        }, "线程1");

        Thread t2= new Thread(()->{
            synchronized (Test.class) {
                System.out.println(Thread.currentThread().getName()+" 获取 Test 锁成功------执行业务逻辑");
                Test.method2();
                Test.method1();
            }
        }, "线程2");


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main 程序执行完成。");
    }

}

class Test{

    public static synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName()+" ------执行 method1 业务逻辑");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method2() {
        try {
            System.out.println(Thread.currentThread().getName()+" ------执行 method2 业务逻辑");
            method1();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}