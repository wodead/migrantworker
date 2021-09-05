package com.migrantworker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author: lz
 * @date: 2021/6/8 21:28
 */
public class TreeMapTest {

    static class Counter {
        Long id;
        int count;

        public Counter(Long id) {
            this.id = id;
        }
    }

    public static Collection<Counter> onlyList(List<Long> counterIds, List<Long> ids) {
        List<Counter> counters = new ArrayList<>(counterIds.size());
        for (Long counterId : counterIds) {
            Counter counter = new Counter(counterId);
            counters.add(counter);
        }
        for (Long id : ids) {
            for (Counter counter : counters) {
                if (counter.id.equals(id)) {
                    counter.count += 1;
                    break;
                }

            }

        }

        return counters;
    }


    public static Collection<Counter> treemap(List<Long> counterIds, List<Long> ids) {
        TreeMap<Long, Counter> counterMap = new TreeMap<>();
        for (Long counterId : counterIds) {
            counterMap.put(counterId, new Counter(counterId));
        }
        for (Long id : ids) {
            counterMap.get(id).count += 1;
        }

        return counterMap.values();
    }

    public static Collection<Counter> mapAndArrayList(List<Long> counterIds, List<Long> ids) {
        HashMap<Long, Counter> counterMap = new HashMap<>();
        List<Counter> counters = new ArrayList<>(counterIds.size());
        for (Long counterId : counterIds) {
            Counter counter = new Counter(counterId);
            counterMap.put(counterId, counter);
            counters.add(counter);
        }

        for (Long id : ids) {
            counterMap.get(id).count += 1;
        }

        return counters;
    }

    public static void main(String[] args) {

        List<Long> counterIds =  LongStream.range(0, 10).boxed().collect(Collectors.toList());
        List<Long> ids =  LongStream.generate(()->(long)(Math.random() * 10)).limit(100000).boxed().collect(Collectors.toList());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
            mapAndArrayList(counterIds, ids);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        for (int i = 0; i < 100; i++)
        start = System.currentTimeMillis();
            treemap(counterIds, ids);
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
            onlyList(counterIds, ids);
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }


}
