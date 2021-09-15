from typing import List


class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        """
        https://leetcode.com/problems/island-perimeter/
        统计重合边的数量

        """
        count, side = 0, 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 1:
                    count += 1
                    if i > 0 and grid[i - 1][j] == 1:
                        side += 1
                    if j > 0 and grid[i][j - 1] == 1:
                        side += 1
        return count * 4 - side * 2
