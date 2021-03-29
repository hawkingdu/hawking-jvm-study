package edu.hawking.classloader;

import sun.misc.Launcher;
import sun.security.pkcs10.PKCS10;

import java.net.URL;

/**
 * 杜皓君 created by 2021/3/27
 * ClassLoadTest
 **/
public class ClassLoadTest {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.security.ec.ECDHKeyAgreement.class.getClassLoader());
        System.out.println(ClassLoadTest.class.getClassLoader());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("默认系统类加载器："+appClassLoader);
        System.out.println("扩展类加载器："+extClassLoader);
        System.out.println("引导类加载器："+bootstrapClassLoader);

        System.out.println();
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("引导类加载器加载以下文件：");
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassloader加载以下文件:");
        System.out.println(System.getProperty("java.class.path"));
    }
}
