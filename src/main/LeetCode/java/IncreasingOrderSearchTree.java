package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/increasing-order-search-tree/
 * 思路简述：dfs, 返回的是子树构成的 order search tree 的头尾节点
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class IncreasingOrderSearchTree {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> res = dfs(root);
        return res.get(0);
    }

    private List<TreeNode> dfs(TreeNode node) {
        List<TreeNode> res = new ArrayList<>();
        if (node.left == null && node.right == null) {
            res.add(node);
            res.add(node);
            return res;
        } else if (node.right == null) {
            List<TreeNode> left = dfs(node.left);
            node.left = null;
            left.get(1).right = node;
            left.set(1, node);
            return left;
        } else if (node.left == null) {
            List<TreeNode> right = dfs(node.right);
            node.right = right.get(0);
            right.set(0, node);
            return right;
        } else {
            List<TreeNode> left = dfs(node.left);
            List<TreeNode> right = dfs(node.right);
            node.left = null;
            left.get(1).right = node;
            node.right = right.get(0);
            left.set(1, right.get(1));
            return left;
        }
    }
}
