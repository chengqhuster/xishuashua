package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-cameras/
 *
 * 思路简述：贪心算法 参考 https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
 *          类似自底向上的DP
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class BinaryTreeCameres {
    private final int NOT_MONITORED = 0;
    private final int CAMERA = 1;
    private final int MONITORED = 2;
    int res = 0;

    public int minCameraCover(TreeNode root) {
        if (dfs(root) == NOT_MONITORED) {
            return res + 1;
        }
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return MONITORED;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == 0 || right == 0) {
            res++;
            return CAMERA;
        }
        return left == CAMERA || right == CAMERA ? MONITORED : NOT_MONITORED;
    }
}
