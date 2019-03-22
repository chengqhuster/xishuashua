package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/symmetric-tree/
 *
 */

import LeetCode.java.DataStruct.TreeLinkNode;

public class SymmetricTree {
    public boolean isSymmetric(TreeLinkNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricCore(root.left, root.right);
    }

    private boolean isSymmetricCore(TreeLinkNode left, TreeLinkNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            if (left.val != right.val) {
                return false;
            } else {
                return isSymmetricCore(left.left, right.right) && isSymmetricCore(left.right, right.left);
            }
        }
    }
}
