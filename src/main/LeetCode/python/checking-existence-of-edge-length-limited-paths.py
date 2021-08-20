from typing import List, Tuple


class Solution:
    def distanceLimitedPathsExist(self, n: int, edgeList: List[List[int]], queries: List[List[int]]) -> List[bool]:
        """
        并查集
        本题的关键在于并查集的合并条件 (节点间距) 是在变化的，从小到达

        """
        union_list = list(range(n))
        query_sort = sorted([(p, q, l, i) for i, (p, q, l) in enumerate(queries)], key=lambda x: x[2])
        edge_sort = sorted([(p, q, l) for p, q, l in edgeList], key=lambda x: x[2])
        res = [False] * len(query_sort)

        def find(a: int) -> Tuple[int, int]:
            dep = 0
            while union_list[a] != a:
                union_list[a] = union_list[union_list[a]]
                a = union_list[a]
                dep += 1
            return a, dep

        def merge(a: int, b: int) -> None:
            a_res, b_res = find(a), find(b)
            if a_res[0] != b_res[0]:
                if a_res[1] > b_res[1]:
                    union_list[b_res[0]] = a_res[0]
                else:
                    union_list[a_res[0]] = b_res[0]

        index = 0
        for p, q, l, i in query_sort:
            while index < len(edge_sort) and edge_sort[index][2] < l:
                merge(edge_sort[index][0], edge_sort[index][1])
                index += 1
            if find(p)[0] == find(q)[0]:
                res[i] = True

        return res
