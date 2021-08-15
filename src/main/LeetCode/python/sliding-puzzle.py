import collections
from typing import List


class Solution:

    def slidingPuzzle(self, board: List[List[int]]) -> int:
        """
        BSF + memory

        """
        s = ''.join(str(_) for row in board for _ in row)
        bq, visited = collections.deque(), {s}
        if 0 in board[0]:
            bq.append((s, 0, board[0].index(0)))
        else:
            bq.append((s, 1, board[1].index(0)))

        step, height, width = 0, len(board), len(board[0])
        while bq:
            for _ in range(len(bq)):
                ss, x, y = bq.popleft()
                if ss == '123450':
                    return step
                for r, c in (x, y + 1), (x, y - 1), (x + 1, y), (x - 1, y):
                    if height > r >= 0 <= c < width:
                        cl = [c for c in ss]
                        cl[x * width + y], cl[r * width + c] = cl[r * width + c], cl[x * width + y]
                        sn = ''.join(cl)
                        if sn not in visited:
                            visited.add(sn)
                            bq.append((sn, r, c))
            step += 1
        return -1
