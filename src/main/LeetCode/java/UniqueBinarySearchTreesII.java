package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * 思路简述：参考 UniqueBinarySearchTrees
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }
        return generateTreesCore(1, n);
    }

    private List<TreeNode> generateTreesCore(int left, int right) {
        List<TreeNode> nodes = new LinkedList<>();
        if (left > right) {
            nodes.add(null);
        }
        if (left == right) {
            nodes.add(new TreeNode(left));
        } else {
            for (int i = left; i <=right; i++) {
                List<TreeNode> leftNodes = generateTreesCore(left, i - 1);
                List<TreeNode> rightNodes = generateTreesCore(i + 1, right);
                for (int m = 0; m < leftNodes.size(); m++) {
                    for (int n = 0; n < rightNodes.size(); n++) {
                        TreeNode node = new TreeNode(i);
                        node.left = leftNodes.get(m);
                        node.right = rightNodes.get(n);
                        nodes.add(node);
                    }
                }
            }
        }
        return nodes;
    }
}
