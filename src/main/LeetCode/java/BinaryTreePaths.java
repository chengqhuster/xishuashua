package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-paths/
 *
 * 思路简述：先序遍历
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePaths {
    private Stack<Integer> st = new Stack<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        binaryTreePathsCore(root, res);
        return res;
    }

    private void binaryTreePathsCore(TreeNode node, List<String> res) {
        st.push(node.val);
        if (node.left != null) {
            binaryTreePathsCore(node.left, res);
        }
        if (node.right != null) {
            binaryTreePathsCore(node.right, res);
        }
        if (node.left == null && node.right == null) {
            Iterator<Integer> it = st.iterator();
            StringBuffer sb = new StringBuffer();
            sb.append(it.next());
            while (it.hasNext()) {
                sb.append( "->").append(it.next());
            }
            res.add(sb.toString());
        }
        st.pop();
    }
}
