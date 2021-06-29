package edu.hawking.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 杜皓君 created by 2021/5/13
 * TryLockDeadlock
 * 类描述:  使用lock接口提供的trylock 避免死锁
 **/
public class TryLockDeadlock implements Runnable {
    int flag = 1;
    //ReentrantLock 为可重入锁
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        // 创建两个线程 给出不同的flag  并启动
        TryLockDeadlock r1 = new TryLockDeadlock();
        TryLockDeadlock r2 = new TryLockDeadlock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1, "线程1").start();
        new Thread(r2, "线程2").start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) { //先获取锁1  再获取锁2
                try {
                    //给锁1 800毫秒与获取锁, 如果拿到锁, 返回true, 反之返回false
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "------获取到了锁 lock1  ");
                        //随机的休眠
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "------获取到了锁 lock2  ");
                            System.out.println(Thread.currentThread().getName() + " 成功获取了lock1 lock2 ");
                            //释放两把锁, 退出循环
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "------获取锁 lock2 失败， 准备重试 ");
                            //随机的休眠
                            Thread.sleep(new Random().nextInt(1000));


                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "------获取锁 lock1 失败， 准备重试 ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放锁1
                    System.out.println(Thread.currentThread().getName() + "------释放 lock1");
                    lock1.unlock();
                }
            }
            if (flag == 0) { //先获取锁2  再获取锁1. 并且尝试获取锁的时间变长 ,改成3s
                try {
                    //给锁1 800毫秒与获取锁, 如果拿到锁, 返回true, 反之返回false
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "------获取到了锁 lock2  ");
                        //随机的休眠
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "------获取到了锁 lock1  ");
                            System.out.println(Thread.currentThread().getName() + " 成功获取了lock1 lock2 ");
                            //释放两把锁, 退出循环
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "------获取锁 lock1 失败， 准备重试 ");
                            //随机的休眠
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "------获取锁 lock2 失败， 准备重试 ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "------释放 lock2");
                    //释放锁1
                    lock2.unlock();
                }
            }
        }
    }
}
