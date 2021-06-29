package edu.hawking.collections;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 杜皓君 created by 2021/6/20
 * MyBlockingQueueTest
 **/
public class MyBlockingQueueTest {

    public static void main(String[] args) {
        MyBlockingQueue2<Integer> queue = new MyBlockingQueue2<>(0);
        new Thread(()->{
            while (true) {
                Random r = new Random();
                int i = r.nextInt(10);
                System.out.println("放入数字："+ i);
                queue.push(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                System.out.println("取出结果："+queue.pop());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("队列大小："+queue.size());
        }, 1, 500, TimeUnit.MILLISECONDS);
    }
}
