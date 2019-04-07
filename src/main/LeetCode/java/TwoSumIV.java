package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 *
 * 思路简述：
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            if (set.contains(k - node.val)) {
                return true;
            }
            set.add(node.val);
            if (node.right != null) {
                st.push(node.right);
            }
            if (node.left != null) {
                st.push(node.left);
            }
        }
        return false;
    }
}
