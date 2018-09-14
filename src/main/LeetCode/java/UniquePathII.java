package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-paths-ii/description/
 *
 * 思路简述：动态规划 注意状态转移即可
 *
 */

public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = -1;
            }
        }
        for(int i=0; i<m; i++) {
            if(obstacleGrid[i][0] == -1)
                break;
            obstacleGrid[i][0] = 1;
        }
        for(int i=0; i<n; i++) {
            if(obstacleGrid[0][i] == -1)
                break;
            obstacleGrid[0][i] = 1;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j] == -1)
                    continue;
                obstacleGrid[i][j] = Math.max(obstacleGrid[i-1][j], 0) + Math.max(obstacleGrid[i][j-1], 0);
            }
        }
        return Math.max(obstacleGrid[m-1][n-1], 0);
    }
}
