package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/smallest-string-starting-from-leaf/
 *
 * 思路简述：dfs，先序遍历 记录经过的节点
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SmallestStringStartingFromLeaf {
    String res = null;

    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<Character> list = new ArrayList<>();
        list.add((char)('a' + root.val));
        dfs(root, list);
        return res;
    }

    private void dfs(TreeNode node, List<Character> list) {
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Character c : list) {
                sb.append(c);
            }
            String str = sb.reverse().toString();
            if (res == null || res.compareTo(str) > 0) {
                res = str;
            }
        }
        if (node.left != null) {
            list.add((char)('a' + node.left.val));
            dfs(node.left, list);
            list.remove(list.size() - 1);
        }
        if (node.right != null) {
            list.add((char)('a' + node.right.val));
            dfs(node.right, list);
            list.remove(list.size() - 1);
        }
    }
}
