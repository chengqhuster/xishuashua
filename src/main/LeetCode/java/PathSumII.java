package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/path-sum-ii/
 *
 * 思路简述：先序遍历计算路径节点值之和，用栈保存遍历时的路径
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        Stack<Integer> st = new Stack<>();
        getPath(root, res, st, sum);
        return res;
    }

    private void getPath(TreeNode node, List<List<Integer>> res, Stack<Integer> stack, int sum)  {
        if (node == null) {
            return;
        }
        stack.push(node.val);
        if (node.left == null && node.right == null) {
            if (sum == node.val) {
                List<Integer> path = new LinkedList<>();
                Iterator<Integer> it = stack.iterator();
                while (it.hasNext()) {
                    path.add(it.next());
                }
                res.add(path);
            }
        } else {
            getPath(node.left, res, stack, sum - node.val);
            getPath(node.right, res, stack, sum - node.val);
        }
        stack.pop();
    }
}
