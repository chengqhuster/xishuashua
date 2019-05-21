package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 *
 * 思路简述：二叉数的分层遍历，用队列实现
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode headNode = root;
        int max = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (headNode == node) {
                if (headNode != root) {
                    res.add(max);
                }
                max = node.val;
                headNode = null;
            } else {
                max = Math.max(max, node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
                if (headNode == null) {
                    headNode = node.left;
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                if (headNode == null) {
                    headNode = node.right;
                }
            }
        }
        res.add(max);
        return res;
    }
}
