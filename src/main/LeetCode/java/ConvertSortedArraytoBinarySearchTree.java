package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/submissions/
 *
 * 思路简述：每次都取中间的数作为根节点的值
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBSTCore(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTCore(int[] nums, int left, int right) {
        int middle = (left + right)/2;
        TreeNode node = new TreeNode(nums[middle]);
        if (middle == left) {
            node.left = null;
        } else if (middle == left + 1) {
            node.left = new TreeNode(nums[left]);
        } else {
            node.left = sortedArrayToBSTCore(nums, left, middle - 1);
        }
        if (middle == right) {
            node.right = null;
        } else if (middle + 1 == right) {
            node.right = new TreeNode(nums[right]);
        } else {
            node.right = sortedArrayToBSTCore(nums, middle + 1, right);
        }
        return node;
    }
}
