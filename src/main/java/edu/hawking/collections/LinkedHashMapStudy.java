package edu.hawking.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 杜皓君 created by 2021/5/8
 * LinkedHashMapStudy
 **/
public class LinkedHashMapStudy {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>(10,10);
        for (int i = 0; i < 50; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        System.out.print("LinkedHashMap: ");
        System.out.println(map);

        Map<String, String> hashMap = new HashMap<>(10,10);
        for (int i = 0; i < 50; i++) {
            hashMap.put(String.valueOf(i), String.valueOf(i));
        }
        System.out.print("HashMap: ");
        System.out.println(hashMap);
    }
}
