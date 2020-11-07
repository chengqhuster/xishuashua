package LeetCode.java;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 题目描述：https://leetcode.com/problems/task-scheduler/
 *
 * 思路简述：剩余数量多的任务具有较高的执行优先级
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> characterMap = new HashMap<>();
        for (char task : tasks) {
            characterMap.compute(task, (k, v) -> v == null ? 1 : v + 1);
        }
        PriorityQueue<CharacterWithCount> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        characterMap.forEach((key, value) -> priorityQueue.add(new CharacterWithCount(key, value)));
        Deque<CharacterWithCount> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            deque.offerLast(null);
        }
        int total = tasks.length;
        int res = 0;
        while (total > 0) {
            if (priorityQueue.isEmpty()) {
                deque.offerLast(null);
            } else {
                CharacterWithCount executeChar = priorityQueue.poll();
                executeChar.reduceCount();
                deque.offerLast(executeChar);
                total--;
            }
            CharacterWithCount releaseChar = deque.pollFirst();
            if (releaseChar != null && !releaseChar.isZeroCount()) {
                priorityQueue.add(releaseChar);
            }
            res++;
        }
        return res;
    }

    static class CharacterWithCount implements Comparable<CharacterWithCount> {

        private final char c;
        private int count;

        public CharacterWithCount(char c, int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("COUNT MUST BE POSITIVE");
            }
            this.c = c;
            this.count = count;
        }

        public void reduceCount() {
            count--;
        }

        private boolean isZeroCount() {
            return count == 0;
        }

        @Override
        public int compareTo(CharacterWithCount o) {
            if (this.count == o.count) {
                return this.c - o.c;
            } else {
                return this.count - o.count;
            }
        }
    }
}
