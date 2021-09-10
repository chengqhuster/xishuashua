from typing import List


class Solution:
    # 上、右、下、左
    directs = ((-1, 0), (0, 1), (1, 0), (0, -1))

    def spiralMatrixIII(self, rows: int, cols: int, rStart: int, cStart: int) -> List[List[int]]:
        """
        https://leetcode.com/problems/spiral-matrix-iii/
        拓展为大的矩阵，能够满足所有移动的位置

        """
        half_len = max(rStart, cStart, rows - 1 - rStart, cols - 1 - cStart)
        square_len = half_len * 2 + 1
        extend_matrix = [[False] * square_len for _ in range(square_len)]
        # 偏移量
        shift = (half_len - rStart, half_len - cStart)
        # 运动、标记
        r_pos, c_pos = half_len, half_len
        extend_matrix[r_pos][c_pos] = True
        res = []
        direct = 0

        def next_direct():
            nonlocal direct
            direct = direct + 1 if direct < 3 else 0
            return Solution.directs[direct]

        def prev_direct():
            nonlocal direct
            direct = direct - 1 if direct > 0 else 3
            return Solution.directs[direct]

        def try_move(dire):
            nonlocal r_pos, c_pos
            if 0 <= r_pos + dire[0] < square_len and 0 <= c_pos + dire[1] < square_len and not \
                    extend_matrix[r_pos + dire[0]][c_pos + dire[1]]:
                r_pos, c_pos = r_pos + dire[0], c_pos + dire[1]
                extend_matrix[r_pos][c_pos] = True
                return True
            return False

        while True:
            original_row, original_col = r_pos - shift[0], c_pos - shift[1]
            if 0 <= original_row < rows and 0 <= original_col < cols:
                res.append([original_row, original_col])
            # 下一个位置
            if try_move(next_direct()):
                continue
            if try_move(prev_direct()):
                continue
            break
        return res
