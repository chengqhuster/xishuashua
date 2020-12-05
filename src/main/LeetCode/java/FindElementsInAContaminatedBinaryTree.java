package LeetCode.java;

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
 *
 * 思路简述：根据节点值还原根结点到该节点的路径
 */
public class FindElementsInAContaminatedBinaryTree {
    private final TreeNode root;

    public FindElementsInAContaminatedBinaryTree(TreeNode root) {
        recover(root);
        this.root = root;
    }

    private void recover(TreeNode node) {
        if (node.val == -1) {
            node.val = 0;
        }
        int val = node.val;
        if (node.left != null) {
            TreeNode left = node.left;
            left.val = val * 2 + 1;
            recover(left);
        }
        if (node.right != null) {
            TreeNode right = node.right;
            right.val = val * 2 + 2;
            recover(right);
        }
    }

    public boolean find(int target) {
        TreeNode node = root;
        List<Integer> path = getDirectPath(target);
        for (Integer direct : path) {
            if (direct == -1) {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getDirectPath(int target) {
        // 1 代表 right, -1 代表 left
        List<Integer> path = new ArrayList<>();
        while (target != 0) {
            if ((target & 1) == 0) {
                path.add(1);
                target = (target - 2) / 2;
            } else {
                path.add(-1);
                target = (target - 1) / 2;
            }
        }
        Collections.reverse(path);
        return path;
    }
}
