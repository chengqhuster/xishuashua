package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * 思路简述：保存路径节点的非递归先序遍历，到叶节点的时候计算路径值
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.Iterator;
import java.util.Stack;

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        if (root == null) {
            return 0;
        }
        st.push(root);
        int res = 0;
        // 保存路径节点的非递归先序遍历
        while (!st.isEmpty()) {
            TreeNode node = st.peek();
            if (node.left != null) {
                st.push(node.left);
            } else if (node.right != null) {
                st.push(node.right);
            } else {
                Iterator<TreeNode> it = st.iterator();
                int pathNum = 0;
                while (it.hasNext()) {
                    pathNum = pathNum*10 + it.next().val;
                }
                res += pathNum;
                TreeNode a, b;
                do {
                    a = st.pop();
                    if (st.isEmpty()) {
                        b = null;
                        break;
                    } else {
                        b = st.peek();
                    }
                } while (a == b.right || b.right == null);
                if (b != null) {
                    st.push(b.right);
                }
            }
        }
        return res;
    }
}
