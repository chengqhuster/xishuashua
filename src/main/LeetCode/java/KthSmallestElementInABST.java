package LeetCode.java;

import LeetCode.java.DataStruct.TreeNode;

/**
 * 题目描述：https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * 思路简述：先序遍历
 */
public class KthSmallestElementInABST {

    public int kthSmallest(TreeNode root, int k) {
        int[] start = new int[]{0, 0};
        core(root, start, k);
        return start[1];
    }

    private void core(TreeNode node, int[] temp, int target) {
        if (temp[0] == target || node == null) {
            return;
        }
        if (node.left != null) {
            core(node.left, temp, target);
        }
        if (temp[0] == target) {
            return;
        }
        temp[0]++;
        temp[1] = node.val;
        if (node.right != null) {
            core(node.right, temp, target);
        }
    }
}
