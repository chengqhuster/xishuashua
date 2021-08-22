from typing import List, Tuple


class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        """
        直接按照目标的座位执行交换

        """
        index_map = {num: index for index, num in enumerate(row)}

        def arrange(a: int) -> int:
            count = 0
            while True:
                # 去掉最后一位 1（存在的话），保证是偶数位
                a = (a >> 1) << 1
                if (row[a] == row[a + 1] + 1 and row[a] % 2 == 1) or (row[a + 1] == row[a] + 1 and row[a] % 2 == 0):
                    break
                if row[a] % 2 == 0:
                    target = index_map[row[a] + 1]
                else:
                    target = index_map[row[a] - 1]
                # 执行交换
                row[a + 1], row[target] = row[target], row[a + 1]
                index_map[row[a + 1]], index_map[row[target]] = target, a + 1
                a = target
                count += 1
            return count

        return sum([arrange(pos) for pos in range(0, len(row), 2)])

    def minSwapsCouples_uf(self, row: List[int]) -> int:
        """
        faster
        也可使用并交集的思路，以（2i, 2i + 1）为单元，对不符合座位条件的组合进行并交集运算
        合并的条件是两个组合内均有属于一对夫妻的其中一个编号
        最终的交换次数是 sum{ 并集元素数量 - 1 }

        """
        n = len(row) >> 1
        index_map = {num: index >> 1 for index, num in enumerate(row)}
        union_list = list(range(n))

        def find(a: int) -> int:
            while union_list[a] != a:
                union_list[a] = union_list[union_list[a]]
                a = union_list[a]
            return a

        def merge(a: int, b: int) -> None:
            a_res, b_res = find(a), find(b)
            union_list[b_res] = a_res

        for num in range(0, len(row), 2):
            merge(index_map[num], index_map[num + 1])

        return n - len(set([find(x) for x in union_list]))
