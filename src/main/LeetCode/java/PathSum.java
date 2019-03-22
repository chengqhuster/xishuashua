package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/path-sum/
 *
 * 思路简述：先序遍历计算路径节点值之和
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return sum == root.val;
        } else if (root.right == null) {
            return hasPathSum(root.left, sum - root.val);
        } else if (root.left == null) {
            return hasPathSum(root.right, sum - root.val);
        } else {
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}
