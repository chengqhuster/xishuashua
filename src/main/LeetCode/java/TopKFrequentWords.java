package LeetCode.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目描述：https://leetcode.com/problems/top-k-frequent-words/
 *
 * 思路简述：先统计，再排序
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.compute(word, (w, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }
        List<StringWithCount> stringWithCounts = wordCountMap.entrySet()
                .stream()
                .map(entry -> new StringWithCount(entry.getKey(), entry.getValue()))
                .sorted()
                .collect(Collectors.toList());
        return stringWithCounts.subList(0, k).stream().map(StringWithCount::getString).collect(Collectors.toList());
    }

    class StringWithCount implements Comparable<StringWithCount> {
        private String s;
        private int count;

        public StringWithCount(String s, int count) {
            this.s = s;
            this.count = count;
        }

        public String getString() {
            return s;
        }

        // count大、字母序小的在前面，自然排序结果小的在前面
        @Override
        public int compareTo(StringWithCount o) {
            if (o == null) {
                return 1;
            }
            if (this.count != o.count) {
                return o.count - this.count;
            }
            int len = Math.min(this.s.length(), o.s.length());
            for (int i = 0; i < len; i++) {
                int diff = this.s.charAt(i) - o.s.charAt(i);
                if (diff != 0) {
                    return diff;
                }
            }
            if (this.s.length() != o.s.length()) {
                return this.s.length() - o.s.length();
            }
            return 0;
        }
    }
}
