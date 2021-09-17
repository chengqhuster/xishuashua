from typing import List


class Solution:
    def minimizeTheDifference(self, mat: List[List[int]], target: int) -> int:
        """
        https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/
        mat 和 target 的大小有限

        """
        # 排序
        for i in range(len(mat)):
            mat[i].sort()
        # 最小可能的值，超过target直接返回
        min_pos = sum(row[0] for row in mat)
        if min_pos >= target:
            return min_pos - target
        # 记录所有可能的结果值
        pos = {0}
        for i in range(len(mat)):
            next_pos = set()
            for j in range(len(mat[0])):
                for e in sorted(pos):
                    # 允许的最大值
                    if e + mat[i][j] <= target + 70:
                        next_pos.add(e + mat[i][j])
                    else:
                        break
            pos = next_pos
        return min(abs(item - target) for item in pos)
