package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 *
 * 思路简述：dfs，树的先序遍历
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class MaximumDifferenceBetweenNodeAndAncestor {
    int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return res;
        }
        dfs(root, root.val, root.val);
        return res;
    }

    private void dfs(TreeNode node, int max, int min) {
        int val = node.val;
        int temp = Math.max(Math.abs(max - val), Math.abs(min - val));
        res = Math.max(res, temp);
        max = Math.max(max, val);
        min = Math.min(min, val);
        if (node.left != null) {
            dfs(node.left, max, min);
        }
        if (node.right != null) {
            dfs(node.right, max, min);
        }
    }
}
