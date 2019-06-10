package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/
 *
 * 思路简述：dfs
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTreeToMatchPreorderTraversal {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if (dfs(root, voyage, 0, voyage.length, res)) {
            return res;
        } else {
            res.clear();
            res.add(-1);
            return res;
        }
    }

    private boolean dfs(TreeNode node, int[] voyage, int start, int end, List<Integer> res) {
        if (node.val != voyage[start]) {
            return false;
        }
        if (end - start == 1) {
            return true;
        }
        if (node.left != null && node.right != null) {
            if (node.left.val == voyage[start + 1]) {
                int r = findPos(voyage, start, end, node.right.val);
                if (r == -1) {
                    return false;
                }
                return dfs(node.left, voyage, start + 1, r, res) && dfs(node.right, voyage, r, end, res);
            } else if (node.right.val == voyage[start + 1]) {
                res.add(node.val);
                int l = findPos(voyage, start, end, node.left.val);
                if (l == -1) {
                    return false;
                }
                return dfs(node.left, voyage, l, end, res) && dfs(node.right, voyage, start + 1, l, res);
            } else {
                return false;
            }
        } else if (node.left == null) {
            if (node.right == null) {
                return false;
            }
            if (node.right.val == voyage[start + 1]) {
                return dfs(node.right, voyage, start + 1, end, res);
            } else {
                return false;
            }
        } else {
            if (node.left.val == voyage[start + 1]) {
                return dfs(node.left, voyage, start + 1, end, res);
            } else {
                return false;
            }
        }
    }

    private int findPos(int[] voyage, int start, int end, int target) {
        for (int i = start; i < end; i++) {
            if (voyage[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
