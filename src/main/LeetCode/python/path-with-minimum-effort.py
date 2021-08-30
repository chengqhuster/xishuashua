from itertools import product
from typing import List, Tuple


class Solution:
    moves = [(1, 0), (0, 1), (-1, 0), (0, -1)]

    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        """
        https://leetcode.com/problems/path-with-minimum-effort/
        并交集，合并范围逐步增大

        """
        m, n = len(heights), len(heights[0])
        if m * n == 1:
            return 0
        union_list = [_ for _ in range(m * n)]

        def find(a: int) -> int:
            while a != union_list[a]:
                union_list[a] = union_list[union_list[a]]
                a = union_list[a]
            return a

        def merge(a: int, b: int) -> None:
            a_root, b_root = find(a), find(b)
            if a_root != b_root:
                union_list[a_root] = b_root

        # 构建path，元素为三元祖（起始节点、目的节点、距离），move只取了向下和向右两个方向
        path_info = [
            (i * n + j, (i + move[0]) * n + j + move[1], abs(heights[i][j] - heights[i + move[0]][j + move[1]]))
            for i, j, move in product(range(m), range(n), Solution.moves[0:2])
            if i + move[0] < m and j + move[1] < n
        ]
        # 自然升序
        path_info.sort(key=lambda x: x[2])

        min_effort = path_info[0][2]
        index = 0
        while True:
            while index < len(path_info) and path_info[index][2] <= min_effort:
                merge(path_info[index][0], path_info[index][1])
                index += 1
            if find(0) == find(m * n - 1):
                return min_effort
            else:
                min_effort = path_info[index][2]

    def minimumEffortPathError(self, heights: List[List[int]]) -> int:
        """
        https://leetcode.com/problems/path-with-minimum-effort/
        贪心算法，每次只增加最短的下个节点（或者节点集合），类似最短路径的 D 算法
        理解错误：最短effort是整条路径上最大间隔的最小值，而不是整条路径的最短长度

        """
        m, n = len(heights), len(heights[0])
        # (i, j) 位置用 i * n + j 作为 key，value 为最短路径长度
        min_path = {0: 0}
        ignore = set()

        while True:
            if m * n - 1 in min_path:
                return min_path[m * n - 1]
            candidate = {}
            for pos_key, pos_len in min_path.items():
                if pos_key in ignore:
                    continue
                stable = True
                for move in Solution.moves:
                    a, b = int(pos_key / n), pos_key % n
                    c, d = a + move[0], b + move[1]
                    if 0 <= c < m and 0 <= d < n and c * n + d not in min_path:
                        stable = False
                        next_len = pos_len + abs(heights[a][b] - heights[c][d])
                        if c * n + d not in candidate:
                            candidate[c * n + d] = next_len
                        else:
                            candidate[c * n + d] = min(candidate[c * n + d], next_len)
                if stable:
                    ignore.add(pos_key)

            ca_list = sorted(candidate.items(), key=lambda it: it[1])
            for ca in ca_list:
                if ca[1] == ca_list[0][1]:
                    min_path[ca[0]] = ca[1]
                else:
                    break
