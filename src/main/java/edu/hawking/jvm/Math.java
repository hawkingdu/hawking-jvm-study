package edu.hawking.jvm;

import edu.hawking.pojo.BigObject;

/**
 * 杜皓君 created by 2021/3/26
 * Math
 **/
public class Math {

    String s1 = "123";

    public static int initData = 66;
    public static BigObject bigObject = new BigObject();

    public int compute() {
        int a = 2;
        int b = 3;
        int c = a+b;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        System.out.println(math.compute());
    }
}
