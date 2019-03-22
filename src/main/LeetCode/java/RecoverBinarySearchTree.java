package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/recover-binary-search-tree/
 *
 * 思路简述：参考 ValidateBinarySearchTree 用store记录遍历过程中异常的节点
 *          异常的节点为下降之处，可能为1或2处
 *         5                     5              4
 *       4                     4                      5
 *     3     ---------->   3              or      3
 *   2                       2                      2
 * 1                     1                    1
 *
 */

import LeetCode.java.DataStruct.TreeLinkNode;

public class RecoverBinarySearchTree {
    TreeLinkNode prev;
    TreeLinkNode[] store = new TreeLinkNode[3];

    public void recoverTree(TreeLinkNode root) {
        judgeNode(root);
        if (store[2] != null) {
            int temp = store[0].val;
            store[0].val = store[2].val;
            store[2].val = temp;
        } else {
            int temp = store[0].val;
            store[0].val = store[1].val;
            store[1].val = temp;
        }
    }

    private void judgeNode(TreeLinkNode node) {
        if (node.left != null) {
            judgeNode(node.left);
        }

        if (prev != null && node.val <= prev.val) {
            if (store[0] == null) {
                store[0] = prev;
                store[1] = node;
            } else {
                store[2] = node;
            }
        }
        prev = node;

        if (node.right != null) {
            judgeNode(node.right);
        }
    }
}
