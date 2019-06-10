package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 *
 * 思路简述：simple dfs
 *
 */

import LeetCode.java.DataStruct.Node;

public class MaximumDepthOfNAryTree {
  public int maxDepth(Node root) {
    if (root == null) {
      return 0;
    }
    return dfs(root);
  }

  private int dfs(Node node) {
    int depth = 0;
    for (Node n : node.neighbors) {
      depth = Math.max(depth, dfs(n));
    }
    return depth + 1;
  }
}
