from typing import List
from typing import Tuple
from collections import deque


class Solution:
    def calcEquation(self, equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:
        """
        并查集，通过 map 维护
        宽度优先遍历

        """
        # 并查集
        union_map = {x: x for ss in equations for x in ss}

        def find(a: str) -> Tuple[str, int]:
            dep = 0
            while union_map[a] != a:
                union_map[a] = union_map[union_map[a]]
                dep += 1
                a = union_map[a]
            return a, dep

        def merge(a: str, b: str) -> None:
            a_res, b_res = find(a), find(b)
            if a_res[0] != b_res[0]:
                if a_res[1] > b_res[1]:
                    union_map[b_res[0]] = a_res[0]
                else:
                    union_map[a_res[0]] = b_res[0]
        # 合并
        for ss in equations:
            merge(ss[0], ss[1])

        # 建立索引，变量与关联的等式
        equation_map ={}
        for index, ss in enumerate(equations):
            if ss[0] not in equation_map:
                equation_map[ss[0]] = set()
            if ss[1] not in equation_map:
                equation_map[ss[1]] = set()
            equation_map[ss[0]].add(index)
            equation_map[ss[1]].add(index)

        # 每个独立的并查集根赋值单位 1
        num_map = {x: 1.0 for x in union_map if x == union_map[x]}
        # BSF 深度遍历，给每个变量赋值
        dq = deque(num_map.keys())
        while dq:
            for _ in range(len(dq)):
                v = dq.popleft()
                for equation_index in equation_map[v]:
                    if equations[equation_index][0] not in num_map:
                        num_map[equations[equation_index][0]] = \
                            num_map[equations[equation_index][1]] * values[equation_index]
                        dq.append(equations[equation_index][0])
                    if equations[equation_index][1] not in num_map:
                        num_map[equations[equation_index][1]] = \
                            num_map[equations[equation_index][0]] / values[equation_index]
                        dq.append(equations[equation_index][1])

        res = []
        for ss in queries:
            # 判断是否是并查集成员
            if ss[0] not in union_map or ss[1] not in union_map:
                res.append(-1.0)
            # 判断是否属于同一个 union
            elif find(ss[0])[0] == find(ss[1])[0]:
                res.append(num_map[ss[0]] / num_map[ss[1]])
            else:
                res.append(-1.0)

        return res
