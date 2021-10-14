package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/*
 * 题目描述：https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 *
 * 思路简述：按照树的遍历方式，0 为根节点
 *
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    int dfs(List<List<Integer>> al, boolean[] visited, int from) {
        int change = 0;
        visited[from] = true;
        for (int to : al.get(from))
            if (!visited[Math.abs(to)])
                change += dfs(al, visited, Math.abs(to)) + (to > 0 ? 1 : 0);
        return change;
    }
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            al.add(new ArrayList<>());
        for (int[] c : connections) {
            al.get(c[0]).add(c[1]);
            al.get(c[1]).add(-c[0]);
        }
        return dfs(al, new boolean[n], 0);
    }
}
