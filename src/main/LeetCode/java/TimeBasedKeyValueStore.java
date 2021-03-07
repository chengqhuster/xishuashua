package LeetCode.java;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 题目描述：https://leetcode.com/problems/time-based-key-value-store/
 *
 * 思路简述：哈希 + 搜索二叉树（红黑树）
 */
public class TimeBasedKeyValueStore {

    Map<String, TreeSet<ValueWithTime>> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeSet<>());
        }

        TreeSet<ValueWithTime> set = map.get(key);
        set.add(new ValueWithTime(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeSet<ValueWithTime> set = map.get(key);
        ValueWithTime target = set.floor(new ValueWithTime(key, timestamp));
        return target == null ? "" : target.value;
    }

    static class ValueWithTime implements Comparable<ValueWithTime> {

        private int timeStamp;
        private String value;

        public ValueWithTime(String value, int timestamp) {
            this.value = value;
            this.timeStamp = timestamp;
        }

        @Override
        public int compareTo(ValueWithTime o) {
            return timeStamp - o.timeStamp;
        }

        @Override
        public int hashCode() {
            return timeStamp;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ValueWithTime)) {
                return false;
            }
            ValueWithTime valueWithTime = (ValueWithTime) obj;
            return valueWithTime.timeStamp == timeStamp && valueWithTime.value.equals(value);
        }
    }
}
