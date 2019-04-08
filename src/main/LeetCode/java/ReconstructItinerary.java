package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/reconstruct-itinerary/
 *
 * 思路简述：dfs 用优先队列来按照字母序遍历
 *
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItinerary {
    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> route = new LinkedList<>();

    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) {
            return route;
        }
        for (String[] path : tickets) {
            // 了解 computeIfAbsent 用法（返回的是value）
            map.computeIfAbsent(path[0], k -> new PriorityQueue<>()).add(path[1]);
        }
        dfs("JFK");
        return route;
    }

    // tickets 是合理（一定能够组一趟行程）的才能这样搜索
    private void dfs(String depart) {
        while (!map.isEmpty() && map.containsKey(depart) && !map.get(depart).isEmpty()) {
            dfs(map.get(depart).poll());
        }
        route.addFirst(depart);
    }
}
