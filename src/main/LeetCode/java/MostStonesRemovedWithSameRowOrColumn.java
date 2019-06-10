package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 *
 * 思路简述： 参考 https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/discuss/197668/
 *              https://www.jianshu.com/p/30d2058db7f7
 *
 *          1.dfs 的思想找出所有横纵坐标相同的节点（组成 island）, 行列可以统一到一个维度（比如 column 的范围是 N ~ 2N-1）
 *          2.横纵坐标统一后，可以使用并查集的思想（纵坐标直接映射到负数空间）
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

  HashMap<Integer, Integer> map = new HashMap<>();
  int islands = 0;

  public int removeStonesUF(int[][] stones) {
    for (int[] pos : stones) {
      union(pos[0], ~pos[1]);
    }
    return stones.length - islands;
  }

  public int find(int x) {
    // putIfAbsent return old value
    if (map.putIfAbsent(x, x) == null) {
      islands++;
    }
    if (x != map.get(x)) {
      //reduce depth
      map.put(x, find(map.get(x)));
    }
    return map.get(x);
  }

  public void union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x != y) {
      map.put(x, y);
      islands--;
    }
  }
}
