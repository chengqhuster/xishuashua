package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * 思路简述：先找出从根节点到起始节点的路径，再从这些路径节点出发，找出对应距离的目的节点 dfs
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<TreeNode> path = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if (!getPath(root, target, path)) {
            // target doesn't not exist in binary tree
            return res;
        }
        int len = path.size();
        // deal with father nodes
        for (int i = 0; i < len - 1; i++) {
            int leftDis = K - (len - 1 - i);
            if (leftDis == 0) {
                res.add(path.get(i).val);
            } else if (leftDis > 0) {
                if (path.get(i + 1) == path.get(i).left) {
                    getNodes(path.get(i).right, leftDis - 1, res);
                } else {
                    getNodes(path.get(i).left, leftDis - 1, res);
                }
            }
        }
        //deal with target itself
        getNodes(target, K, res);
        return res;
    }

    private boolean getPath(TreeNode node, TreeNode target, List<TreeNode> path) {
        path.add(node);
        if (node == target) {
            return true;
        }
        boolean findTarget = (node.left != null && getPath(node.left, target, path)) ||
                (node.right != null && getPath(node.right, target, path));
        if (findTarget) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    private void getNodes(TreeNode node, int dis, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (dis == 0) {
            res.add(node.val);
            return;
        }
        getNodes(node.left, dis - 1, res);
        getNodes(node.right, dis - 1, res);
    }
}
