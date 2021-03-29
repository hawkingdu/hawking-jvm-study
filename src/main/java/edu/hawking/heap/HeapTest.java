package edu.hawking.heap;

import java.util.ArrayList;

/**
 * 杜皓君 created by 2021/3/26
 * HeapTest
 **/
public class HeapTest {
    byte[] bytes = new byte[1024*20]; //20KB

    public HeapTest(){
    }

    /**
     * JVM options:
     * -Xms512M -Xmx512M
     */
    public static void main(String[] args) {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            HeapTest heapTest = new HeapTest();
            heapTests.add(heapTest);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
