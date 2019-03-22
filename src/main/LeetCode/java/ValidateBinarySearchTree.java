package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/validate-binary-search-tree/
 *
 * 思路简述：中序遍历的结果一定是一个排好序的序列，DSF先序遍历时给出每个节点的约束数值范围
 *          注意使用 long 类型，Node的val是int型，可能是最大的int值
 *
 */

import LeetCode.java.DataStruct.TreeLinkNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeLinkNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTCore(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean isValidBSTCore(TreeLinkNode node, long max, long min) {
        if (node.val >= max || node.val <= min) {
            return false;
        }
        boolean resA = true;
        if (node.left != null) {
            resA = isValidBSTCore(node.left, node.val, min);
        }
        boolean resB = true;
        if (node.right != null) {
            resB = isValidBSTCore(node.right, max, node.val);
        }
        return resA && resB;
    }

//    中序遍历，保存上一个节点值

//    TreeNode prev = null;
//    public boolean isValidBST(TreeNode root) {
//        if(root == null) {
//            return true;
//        }
//
//        if(isValidBST(root.left) && (prev == null || root.val > prev.val)) {
//            prev = root;
//            return isValidBST(root.right);
//        }
//
//        return false;
//    }
}
