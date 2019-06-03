package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/distribute-coins-in-binary-tree/
 *
 * 思路简述：参考 BinaryTreeCameras
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class DistributeCoinsInBinaryTree {
    int res = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return  res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        res += Math.abs(left) + Math.abs(right);
        return node.val + left + right - 1;
    }
}
