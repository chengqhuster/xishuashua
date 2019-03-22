package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 思路简述：先序遍历的第一个节点一定为头结点，通过该节点将中序遍历的序列分为左右两个子序列，分别为左子树和右子树的节点集合
 *
 */

import LeetCode.java.DataStruct.TreeLinkNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeLinkNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0
                || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return buildTreeCore(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeLinkNode buildTreeCore(int[] preorder, int pleft, int pright, int[] inorder, int ileft, int iright) {
        TreeLinkNode node = new TreeLinkNode(preorder[pleft]);
        int index = getIndex(preorder[pleft], inorder, ileft, iright);
        if (index == ileft) {
            node.left = null;
        } else {
            node.left = buildTreeCore(preorder, pleft + 1, pleft + 1 + index - ileft, inorder, ileft, index);
        }
        if (index == iright - 1) {
            node.right = null;
        } else {
            node.right = buildTreeCore(preorder, pright - (iright - 1 -index), pright, inorder, index + 1, iright);
        }
        return node;
    }

    private int getIndex(int target, int[] inorder, int left, int right) {
        for (int i = left; i < right; i++) {
            if (target == inorder[i]) {
                return i;
            }
        }
        return -1;
    }
}
