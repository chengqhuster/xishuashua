package LeetCode.java;

import java.util.*;

/**
 * 题目描述：https://leetcode.com/problems/jump-game-iv/
 *
 * 思路简述：1. 转化为图（相同的数字看作一个节点），然后是一个最短路径的问题，但是没有解决具体一个位置上的直接邻居和间接邻居的问题。
 *         2. 直接 bfs，先构建一个数字到索引位置的反向映射
 */
public class JumpGameIV {

    public int minJumps(int[] arr) {
        // 反向映射
        Map<Integer, Set<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!indexMap.containsKey(arr[i])) {
                indexMap.put(arr[i], new HashSet<>());
            }
            indexMap.get(arr[i]).add(i);
        }
        // bfs
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> indexVisited = new HashSet<>();
        Set<Integer> numberVisited = new HashSet<>();
        queue.offer(0);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (indexVisited.contains(index)) {
                    continue;
                }
                indexVisited.add(index);
                if (index == arr.length - 1) {
                    return count;
                }
                if (index - 1 >= 0 && !indexVisited.contains(index - 1)) {
                    queue.offer(index - 1);
                }
                if (index + 1 < arr.length && !indexVisited.contains(index + 1)) {
                    queue.offer(index + 1);
                }
                if (numberVisited.add(arr[index])) {
                    queue.addAll(indexMap.get(arr[index]));
                }
            }
            count++;
        }
        return -1;
    }
}
