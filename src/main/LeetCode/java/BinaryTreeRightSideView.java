package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * 思路简述：树的按层遍历 双队列可以保存每一层的状态
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<TreeNode> q2 = new LinkedList<>();
        q1.addFirst(root);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) {
                res.add(q2.getFirst().val);
                queueConvert(q2, q1);
            } else {
                res.add(q1.getFirst().val);
                queueConvert(q1, q2);
            }
        }
        return res;
    }

    private void queueConvert(LinkedList<TreeNode> q1, LinkedList<TreeNode> q2) {
        while (!q1.isEmpty()) {
            TreeNode node = q1.removeLast();
            if (node.left != null) {
                q2.addFirst(node.left);
            }
            if (node.right != null) {
                q2.addFirst(node.right);
            }
        }
    }
}
