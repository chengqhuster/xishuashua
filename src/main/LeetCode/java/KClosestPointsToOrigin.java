package LeetCode.java;


/*
 * 题目描述：https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * 思路简述：最大堆找出最小的 K 个数
 *
 */

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || K < 1 || K > points.length) {
            return null;
        }
        PriorityQueue<PosInfo> pq = new PriorityQueue<>(K, (o1, o2) -> o2.dis - o1.dis);
        for (int i = 0; i < K; i++) {
            pq.add(new PosInfo(i, points[i]));
        }
        for (int i = K; i < points.length; i++) {
            PosInfo posInfo = new PosInfo(i, points[i]);
            PosInfo max = pq.peek();
            if (max.dis > posInfo.dis) {
                pq.poll();
                pq.add(posInfo);
            }
        }
        int[][] res = new int[K][2];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i] = points[pq.poll().index];
            i++;
        }
        return res;
    }

    public class PosInfo {
        int index;
        int dis;
        public PosInfo(int index, int[] pos) {
            this.index = index;
            this.dis = pos[0]*pos[0] + pos[1]*pos[1];
        }
    }
}
