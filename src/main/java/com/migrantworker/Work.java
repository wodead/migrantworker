package com.migrantworker;

import java.util.*;
import java.util.stream.Collectors;

public class Work {

    public static List<List<String>> fun(List<String> list) {
        if (list == null || list.isEmpty())
            return null;

        Map<String, List<String>> map = new HashMap<>();
        for (String item : list) {
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String sortedItem = new String(chars);
            List<String> result = map.get(sortedItem);
            if (result == null) {
                result = new ArrayList<>();
                map.put(sortedItem, result);
            }
            result.add(item);
        }

        return map.values().stream().collect(Collectors.toList());
    }

    public static void main(String... args) {
        List<String> input = Arrays.asList(new String[]{"eat", "tea", "tan", "ate", "nat", "bata"});
        List<List<String>> output = fun(input);
        if (output != null) {
            for (List<String> result : output) {
                System.out.println(Arrays.deepToString(result.toArray()));
            }
        }
    }
}
