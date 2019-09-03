package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/ones-and-zeroes/
 *
 * 思路简述：图论最短路径 D 算法，注意加上路径长度信息
 *          不能用传统的 D 算法，传统 D 算法会找出源节点到每个节点的最短路径，但是只会每个节点只会计算一次，但是考虑跳数
 *          限制的话，需要更多的计算信息（“冗余”的节点信息）
 *          最小堆 Queue 每次取出来的是下一个最短的路径节点，但是会直接加入由该节点出发的新的路径信息（而不是比较已有的
 *          目的节点距离信息，并取较小的值，因为在跳数的限制下需要考虑所有的路径信息），因为冗余信息的存在，所有会有更多
 *          的计算，特别是可能出现环路计算。
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 构建邻接矩阵
        List<Map<Integer, Integer>> neighbours = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbours.add(new HashMap<>());
        }
        for (int[] flight : flights) {
            neighbours.get(flight[0]).put(flight[1], flight[2]);
        }

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        queue.add(new int[]{src, 0, K + 1});
        while (!queue.isEmpty()) {
            int[] min = queue.poll();
            int city = min[0];
            int price = min[1];
            int hops = min[2];
            if (city == dst) {
                return price;
            }
            if (hops > 0) {
                Map<Integer, Integer> map = neighbours.get(city);
                map.forEach((k, v) -> queue.add(new int[]{k, price + v, hops - 1}));
            }
        }
        return -1;
    }
}
