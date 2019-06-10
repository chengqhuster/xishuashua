package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 *
 * 思路简述： 参考 https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/197668/
 *              https://www.jianshu.com/p/30d2058db7f7
 *
 */

import java.util.HashMap;
import java.util.HashSet;

public class MostStonesRemovedWithSameRowOrColumn {
  HashMap<Integer, HashSet<Integer>> rows = new HashMap<>();
  HashMap<Integer, HashSet<Integer>> cols = new HashMap<>();
  HashSet<Integer> rDfs = new HashSet<>();
  HashSet<Integer> cDfs = new HashSet<>();

  public int removeStonesDFS(int[][] stones) {
    for (int[] pos : stones) {
      rows.compute(pos[0], (k, v) -> {
        if (v == null) {
          v = new HashSet<>();
        }
        v.add(pos[1]);
        return v;
      });
      cols.compute(pos[1], (k, v) -> {
        if (v == null) {
          v = new HashSet<>();
        }
        v.add(pos[0]);
        return v;
      });
    }
    int islands = 0;
    for (int[] pos : stones) {
      if (!rDfs.contains(pos[0])) {
        islands++;
        rowDfs(pos[0]);
        colDfs(pos[1]);
      }
    }
    return stones.length - islands;
  }

  private void rowDfs(int i) {
    rDfs.add(i);
    for (int j : rows.get(i)) {
      if (!cDfs.contains(j)) {
        colDfs(j);
      }
    }
  }

  private void colDfs(int j) {
    cDfs.add(j);
    for (int i : cols.get(j)) {
      if (!rDfs.contains(i)) {
        rowDfs(i);
      }
    }
  }
}
