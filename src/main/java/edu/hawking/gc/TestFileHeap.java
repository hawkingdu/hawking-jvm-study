package edu.hawking.gc;

import edu.hawking.pojo.BigObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 杜皓君 created by 2021/4/1
 * TestFileHeap
 * -Xms1024M -Xmx1024M -Xmn700M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:PretenureSizeThreshold=4M
 * -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\test\fileheap.hprof
 **/
public class TestFileHeap {

    public static void main(String[] args) {
        final String originPath = "C:\\Users\\duhao\\Desktop\\3780045_20210207150315.pdf"; //base64超大文件 至少2M

        final List<String> list = new ArrayList<>();
        List<BigObject> bigObjectList = new ArrayList<>();
        while (true){
                new Thread(()-> {
                    OutputStream outputStream = null;
                    InputStream inputStream = null;
                    String outPath = "D:\\test\\test.pdf";
                    try {
                        inputStream = new FileInputStream(originPath);

                        File file = new File(outPath);
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            outputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[4096];
                            int readLength = 0;
                            while ((readLength = inputStream.read(buffer)) > 0) {
                                byte[] bytes = new byte[readLength];
                                System.arraycopy(buffer, 0, bytes, 0, readLength);
                                outputStream.write(bytes);
                            }
                            outputStream.flush();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    File file = new File(outPath);
                    byte[] fileBytes = file2Byte(file);
                    file.delete();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static byte[] file2Byte(File file) {
        byte[] fileBytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }
}
