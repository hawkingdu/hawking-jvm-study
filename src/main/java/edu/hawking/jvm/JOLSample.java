package edu.hawking.jvm;

import edu.hawking.pojo.BigObject;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * 杜皓君 created by 2021/3/29
 * JOLSample
 **/
public class JOLSample {

    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseClass(TestObject.class);
        System.out.println(layout.toPrintable());

        ClassLayout layout2 = ClassLayout.parseInstance(new byte[0]);
        System.out.println(layout2.toPrintable()); //打印 byte[]对象信息大小

        ClassLayout layout3 = ClassLayout.parseInstance(new byte[1024*20]);
        System.out.println(layout3.toPrintable()); //打印 byte[]对象信息大小

        System.out.println();
        TestObject to = new TestObject();
        //查看对象内部信息
        ClassLayout layout1 = ClassLayout.parseInstance(to);
        System.out.println(layout1.toPrintable());
        //查看对象外部信息
        GraphLayout graphLayout = GraphLayout.parseInstance(to);
        System.out.println(graphLayout.toPrintable());
        //获取对象总大小
        System.out.println("bigObject 总大小："+graphLayout.totalSize());
    }

    // ‐XX:+UseCompressedOops 默认开启的压缩所有指针
    // ‐XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object
    static class TestObject {
        byte[] bytes = new byte[1024*20]; //20KB 对象
        int i; //4B
        long l; //8B
        boolean bool = false; //1B
        byte b; //1B
        String s; //开启指针压缩 4B  禁止指针压缩 8B
    }

}
