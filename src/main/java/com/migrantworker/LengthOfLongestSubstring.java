package com.migrantworker;

import java.util.HashSet;

public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int ret = 0, len = s.length();
        HashSet<Character> set = new HashSet<>();
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0)
                set.remove(s.charAt(i-1));

            for (; j < len && !set.contains(s.charAt(j)); j++) {
                set.add(s.charAt(j));
            }

            if (set.size() > ret)
                ret = set.size();

            if (ret >= len - i -1)
                break;
        }
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("lengthOfLongestSubstring"));
    }
}
