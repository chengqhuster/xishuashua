package LeetCode.java;

import LeetCode.java.DataStruct.TreeLinkNode;

/*
 * 题目描述：https://leetcode.com/problems/insert-into-a-binary-search-tree/
 *
 * 思路简述：按照二叉查找树方式插入即可，没要求树的平衡性
 *
 */

public class InsertIntoABinarySearchTree {
    public TreeLinkNode insertIntoBST(TreeLinkNode root, int val) {
        if (root != null) {
            insertIntoBSTCore(root, val);
        }
        return root;
    }

    public void insertIntoBSTCore(TreeLinkNode root, int val) {
        if (root.val > val) {
            if (root.left == null) {
                TreeLinkNode node = new TreeLinkNode(val);
                root.left = node;
            } else {
                insertIntoBSTCore(root.left, val);
            }
        } else {
            if (root.right == null) {
                TreeLinkNode node = new TreeLinkNode(val);
                root.right = node;
            } else {
                insertIntoBSTCore(root.right, val);
            }
        }
    }
}
