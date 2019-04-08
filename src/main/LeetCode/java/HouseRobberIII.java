package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/house-robber-iii/
 *
 * 思路简述：dfs 每个树的根节点有抢与不抢两种情况 并且对后续的子树有影响 robCoreSec 相比 robCore 减少了重复计算
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] res = robCoreSec(root);
        return Math.max(res[0], res[1]);
    }

    // some duplicated compute (false situation compute twice)
    private int robCore(TreeNode node, boolean target) {
        if (node == null) {
            return 0;
        }
        if (target) {
            return robCore(node.left, false) + robCore(node.right, false) + node.val;
        } else {
            int leftMax = Math.max(robCore(node.left, true), robCore(node.left, false));
            int rightMax = Math.max(robCore(node.right, true), robCore(node.right, false));
            return leftMax + rightMax;
        }
    }

    private int[] robCoreSec(TreeNode node) {
        int[] res = new int[2];
        if (node == null) {
            return res;
        }
        int[] left = robCoreSec(node.left);
        int[] right = robCoreSec(node.right);
        res[0] = left[0] + right[0] + node.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}
