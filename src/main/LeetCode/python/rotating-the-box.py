from typing import List


class Solution:
    def rotateTheBox(self, box: List[List[str]]) -> List[List[str]]:
        """
        https://leetcode.com/problems/rotating-the-box/
        先按重力为 +y 方向处理, 然后旋转数组

        """
        for row in box:
            place = len(row) - 1
            for pos in range(len(row) - 1, -1, -1):
                if row[pos] == '#':
                    row[pos], row[place] = row[place], row[pos]
                    place -= 1
                elif row[pos] == '*':
                    place = pos - 1

        return [[box[r][c] for r in range(len(box) - 1, -1, -1)] for c in range(len(box[0]))]
