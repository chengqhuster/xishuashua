package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/friend-circles/
 *
 * 思路简述：并查集
 *
 */

public class FriendCircles {
    private int group;

    public int findCircleNum(int[][] M) {
        if ( M == null || M.length == 0) {
            return 0;
        }
        int len = M.length;
        group = len;
        int[] id = new int[len];
        for (int i = 0; i < len; i++) {
            id[i] = i;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (M[i][j] == 1) {
                    judgeAndUnion(id, i, j);
                }
            }
        }
        return group;
    }

    //带有平衡过程
    private void judgeAndUnion(int[] id, int x, int y) {
        int[] xId = find(id, x);
        int[] yId = find(id, y);
        if (xId[0] != yId[0]) {
            group--;
            if (xId[1] > yId[1]) {
                id[yId[0]] = id[xId[0]];
            } else {
                id[xId[0]] = id[yId[0]];
            }
        }
    }

    private int[] find(int[] id, int num) {
        int[] res = new int[2];
        int deep = 0;
        while (id[num] != num) {
            id[num] = id[id[num]];
            num = id[num];
            deep++;
        }
        res[0] = num;
        res[1] = deep;
        return res;
    }
}
