import collections
from typing import List


class Solution:
    directs = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    def hitBricks(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        """
        https://leetcode.com/problems/bricks-falling-when-hit/
        改进方式，保存 stable 状态

        """
        # 构建 stable 状态表，在 grid 的第一行上增加一行，该行的元素都是 stable 的
        stable = [[False] * len(grid[0]) for _ in range(len(grid) + 1)]
        stable[0] = [True] * len(grid[0])
        # stable 的数量，不包括第一行
        stable_count = 0

        def calculate_stable(x: int, y: int):
            nonlocal stable_count
            # 增加偏移
            x, y = x + 1, y
            if stable[x][y]:
                return
            stable_brick = False
            for direct in Solution.directs:
                xx, yy = x + direct[0], y + direct[1]
                if 0 <= xx < len(stable) and 0 <= yy < len(stable[0]) and stable[xx][yy]:
                    stable_brick = True
                    break
            if stable_brick:
                stable[x][y] = True
                stable_count += 1
                # BSF
                pos_queue = collections.deque([(x, y)])
                while pos_queue:
                    a, b = pos_queue.popleft()
                    for direct in Solution.directs:
                        aa, bb = a + direct[0], b + direct[1]
                        if 0 <= aa < len(stable) and 0 <= bb < len(stable[0]) \
                                and grid[aa - 1][bb] == 1 and not stable[aa][bb]:
                            stable[aa][bb] = True
                            stable_count += 1
                            pos_queue.append((aa, bb))

        brick_exist: List[bool] = [grid[pos[0]][pos[1]] == 1 for pos in hits]
        for pos in hits:
            grid[pos[0]][pos[1]] = 0
        # 初始数量
        for i in range(len(grid[0])):
            if grid[0][i] == 1:
                calculate_stable(0, i)
        count = [stable_count]
        # 有些 brick 在前面的 hit 就已经掉落了，有些不是，在统计上需要做区分
        ignore_count = 0
        # 逆序回填
        for i in range(len(hits) - 1, -1, -1):
            if brick_exist[i]:
                grid[hits[i][0]][hits[i][1]] = 1
                calculate_stable(hits[i][0], hits[i][1])
                if stable_count > count[-1] + ignore_count:
                    ignore_count += 1
            count.append(stable_count - ignore_count)
        return [count[index] - count[index - 1] for index in range(1, len(hits) + 1)][::-1]

    def hitBricksLTE(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        """
        https://leetcode.com/problems/bricks-falling-when-hit/
        先将所有 hits 的 brick 去掉，再逆序填充回来，计算各阶段 stable 的数量
        TLE 原因是每次都要全量统计一次 stable_count

        """
        brick_exist = [grid[pos[0]][pos[1]] == 1 for pos in hits]
        for pos in hits:
            grid[pos[0]][pos[1]] = 0
        m, n = len(grid), len(grid[0])
        union_merge_list = [[a, grid[int(a / n)][a % n]] for a in range(m * n)]

        def find(a: int) -> int:
            while union_merge_list[a][0] != a:
                union_merge_list[a][0] = union_merge_list[union_merge_list[a][0]][0]
                a = union_merge_list[a][0]
            return a

        def merge(a: int, b: int) -> None:
            # 往数字小的方向 merge
            a_root, b_root = find(a), find(b)
            if a_root < b_root:
                union_merge_list[b_root][0] = a_root
                union_merge_list[b_root][1] += union_merge_list[a_root][1]
            else:
                union_merge_list[a_root][0] = b_root
                union_merge_list[a_root][1] += union_merge_list[b_root][1]

        for x in range(m):
            for y in range(n):
                if grid[x][y] == 1:
                    # 下
                    if x < m - 1 and grid[x + 1][y] == 1:
                        merge(x * n + y, (x + 1) * n + y)
                    # 右
                    if y < n - 1 and grid[x][y + 1] == 1:
                        merge(x * n + y, x * n + y + 1)

        def stable_count() -> int:
            return sum([union_merge_list[x_index][1] for x_index in range(n) if union_merge_list[x_index][0] == x_index])
            # return sum([grid[int(node / n)][node % n] == 1 and find(node) < n for node in range(m * n)])

        count = [stable_count()]
        ignore_count = 0
        # 逆序回填
        for i in range(len(hits) - 1, -1, -1):
            if brick_exist[i]:
                pos = hits[i]
                grid[pos[0]][pos[1]] = 1
                # 上
                if pos[0] > 0 and grid[pos[0] - 1][pos[1]] == 1:
                    merge(pos[0] * n + pos[1], (pos[0] - 1) * n + pos[1])
                # 下
                if pos[0] < m - 1 and grid[pos[0] + 1][pos[1]] == 1:
                    merge(pos[0] * n + pos[1], (pos[0] + 1) * n + pos[1])
                # 左
                if pos[1] > 0 and grid[pos[0]][pos[1] - 1] == 1:
                    merge(pos[0] * n + pos[1], pos[0] * n + pos[1] - 1)
                # 右
                if pos[1] < n - 1 and grid[pos[0]][pos[1] + 1] == 1:
                    merge(pos[0] * n + pos[1], pos[0] * n + pos[1] + 1)
                if find(pos[0] * n + pos[1]) < n:
                    ignore_count += 1
            count.append(stable_count() - ignore_count)

        return [count[index] - count[index - 1] for index in range(1, len(hits) + 1)][::-1]
