package com.migrantworker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: lz
 * @date: 2021/7/1
 */
public class MapVsList {

    public static void main(String... args) {
        long start = 0, end = 0;
        start = System.currentTimeMillis();
        for (int c = 0; c<10000000;c++) {
            List<Integer> list1 = new ArrayList();
            for (int i = 0;i < 200;i++) {
                if (!list1.contains(i))
                    list1.add(i);
            }
        }

        end = System.currentTimeMillis();
        System.out.println("List: " + (end - start));

        start = System.currentTimeMillis();
        for (int c = 0; c<10000000;c++) {
            List<Integer> list2 = new ArrayList();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < 200; i++) {
                if (!map.containsKey(i)) {
                    list2.add(i);
                    map.put(i, i);
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("map: " + (end - start));

        start = System.currentTimeMillis();
        for (int c = 0; c<10000000;c++) {
            LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
            for (int i = 0; i < 200; i++) {
                if (!map.containsKey(i)) {
                    map.put(i, i);
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("map: " + (end - start));

    }
}
