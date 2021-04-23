package edu.hawking.jvm;

import edu.hawking.pojo.BigObject;
import sun.misc.Unsafe;
import sun.misc.VM;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 杜皓君 created by 2021/3/26
 * Math
 **/
public class Math {

    String s1 = "123";

    private volatile int initData;
    private volatile int state;
    private static final long stateOffset;
    private static Unsafe unsafe = getUnsafeInstance();
    public static BigObject bigObject = new BigObject();

    public int compute() {
        int a = 2;
        int b = 3;
        int c = a+b;
        return c;
    }

    static Unsafe getUnsafeInstance() {
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    public static void main(String[] args) {
        Unsafe unsafe = getUnsafeInstance();
        int i = 1;
        System.out.println(stateOffset);
        Math math = new Math();
        boolean bool = unsafe.compareAndSwapInt(math,stateOffset, 0 ,1);
        System.out.println(bool);
        int j = 2;
    }

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(Math.class.getDeclaredField("initData"));

        } catch (Exception ex) { throw new Error(ex); }
    }
}
