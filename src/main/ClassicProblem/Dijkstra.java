package ClassicProblem;

import java.util.Arrays;

/**
 * 最短路径D算法
 */
public class Dijkstra {

    /**
     * 图的矩阵表示，0表示两节点之间没有边
     */
    private int[][] graph;

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    /**
     * 求从pos位置出发到所有节点的最短距离
     * @param pos
     * @return 最短路径长度以及路径信息
     */
    public int[][] shortestPath(int pos) {
        int n = graph.length;
        // 节点是否已知
        boolean[] known = new boolean[n];
        // 到指定点的可能路径长度，MAX代表无限大（未求解）
        int[] shortestDis = new int[n];
        Arrays.fill(shortestDis, Integer.MAX_VALUE);
        // 最短路径的上个节点位置，-1代表没有前置节点
        int[] prevPos = new int[n];

        // 初始化
        shortestDis[pos] = 0;
        prevPos[pos] = -1;

        // 贪心算法
        while (true) {
            int nextPos = -1, nextShortest = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (known[i]) {
                    continue;
                }
                if (shortestDis[i] < nextShortest) {
                    nextPos = i;
                    nextShortest = shortestDis[i];
                }
            }
            if (nextPos == -1)  {
                break;
            }
            // 下一个节点选取nextPos
            known[nextPos] = true;
            for (int i = 0; i < n; i++) {
                if (known[i] || graph[nextPos][i] == 0) {
                    continue;
                }
                if (nextShortest + graph[nextPos][i] < shortestDis[i]) {
                    shortestDis[i] = nextShortest + graph[nextPos][i];
                    prevPos[i] = nextPos;
                }
            }
        }
        return new int[][] {shortestDis, prevPos};
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 2, 0, 1, 0, 0, 0},
                {0, 0, 0, 3, 10, 0, 0},
                {4, 0, 0, 0, 0, 5, 0},
                {0, 0, 2, 0, 2, 8, 4},
                {0, 0, 0, 0, 0, 0, 6},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0}
        };

        Dijkstra dijkstra = new Dijkstra(graph);
        int[][] res = dijkstra.shortestPath(0);
        for (int i = 0; i < res[0].length; i++) {
            System.out.print("node: " + i);
            System.out.print(" dis: " + res[0][i]);
            System.out.println(" prev: " + res[1][i]);
        }
    }
}
