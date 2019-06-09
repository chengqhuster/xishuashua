package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/regions-cut-by-slashes/
 *
 * 思路简述：连通图的点、边、圈的数量满足简单的数学关系式
 *          连通图的个数可以用并查集确定（周围一圈的节点可以手动构建为并集）
 *
 */

public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0) {
            return 0;
        }
        int n = grid.length + 1;
        UF uf = new UF(n * n);
        uf.reset();
        // reactant surround
        for (int i = 0; i < n - 1; i++) {
            uf.union(i, i + 1);
            uf.union(n * (n - 1) + i, n * (n - 1) + i + 1);

            uf.union(i * n, (i + 1) * n);
            uf.union(i * n + n - 1, (i + 1) * n + n - 1);
        }
        int edges = (n - 1) * 4;
        for (int i = 0; i < n - 1; i++) {
            String s = grid[i];
            for (int j = 0; j < n -1; j++) {
                if (s.charAt(j) != ' ') {
                    edges++;
                    if (s.charAt(j) == '/') {
                        uf.union((i + 1) * n + j, i * n + j + 1);
                    } else {
                        uf.union(i * n + j, (i + 1) * n + j + 1);
                    }
                }
            }
        }
        return edges - (n * n) + uf.getSize();
    }

    class UF {
        private int[] id;
        private int size;

        public UF(int size) {
            this.size = size;
            id = new int[size];
        }

        public int getSize() {
            return size;
        }

        public void reset() {
            size = id.length;
            for (int i = 0; i < size; i++) {
                id[i] = i;
            }
        }

        public void union(int x, int y) {
            int[] xx = findWithDeep(x);
            int[] yy = findWithDeep(y);
            if (xx[1] != yy[1]) {
                if (xx[0] > yy[0]) {
                    id[yy[1]] = id[xx[1]];
                } else {
                    id[xx[1]] = id[yy[1]];
                }
                size--;
            }
        }

        public int[] findWithDeep (int t) {
            int deep = 0;
            while (id[t] != t) {
                deep++;
                // reduce tree deep
                id[t] = id[id[t]];
                t = id[t];
            }
            return new int[]{deep, t};
        }
    }
}
