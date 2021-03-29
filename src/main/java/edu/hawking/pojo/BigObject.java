package edu.hawking.pojo;

/**
 * 杜皓君 created by 2021/3/25
 * edu.hawking.pojo.BigObject
 **/
public class BigObject {
    byte[] bytes = new byte[1024*20]; //20KB 对象
    byte[] bytes2 = {1,2,3,4,5,6,7,8,9,0}; //20KB 对象

    int i = Integer.MAX_VALUE; //4B
    long l = Long.MAX_VALUE; //8B
    boolean bool = false; //1B
    byte b; //1B
    String s; //指针压缩 4B 禁止指针压缩 8B

    public BigObject() {

    }
}
