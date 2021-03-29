package edu.hawking.classloader;

import edu.hawking.pojo.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * 杜皓君 created by 2021/3/27
 * MyClassLoader
 **/
public class MyClassLoader extends ClassLoader{
    String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    private byte[] loadFile(String name) {
        String classFullPath = classPath + name.replace('.', '/').concat(".class");
        File file = new File(classFullPath);
        byte[] bytes = null;
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = loadFile(name);
        return super.defineClass(name, classBytes, 0, classBytes.length);
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    if (name.startsWith("edu.hawking")){
                        c = findClass(name);
                    } else {
                        c = this.getParent().loadClass(name);
                    }
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\test\\");
        Class clazz = myClassLoader.loadClass("edu.hawking.pojo.User");
        try {
            Constructor constructor = clazz.getDeclaredConstructor(Long.class, String.class);
            Object obj = constructor.newInstance(1001l, "小李");
            Method toStringMethod = clazz.getDeclaredMethod("toString");
            String s = (String) toStringMethod.invoke(obj);
            System.out.println(clazz);
            System.out.println(s);
            System.out.println(clazz.getClassLoader());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
