package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/leaf-similar-trees/
 *
 * 思路简述：先序遍历
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        dfs(root1, leaf1);
        dfs(root2, leaf2);
        if (leaf1.size() != leaf2.size()) {
            return false;
        }
        for (int i = 0; i < leaf1.size(); i++) {
            if (!leaf1.get(i).equals(leaf2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void dfs(TreeNode node, List<Integer> leafs) {
        if (node.left == null && node.right == null) {
            leafs.add(node.val);
        }
        if (node.left != null) {
            dfs(node.left, leafs);
        }
        if (node.right != null) {
            dfs(node.right, leafs);
        }
    }
}
