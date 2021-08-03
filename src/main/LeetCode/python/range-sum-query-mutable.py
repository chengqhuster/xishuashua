from typing import List


class RangeSumQueryMutable:

    def __init__(self, nums: List[int]):
        """
        https://leetcode.com/problems/range-sum-query-mutable/
        构建树状数组

        :param nums: 原始数组
        """
        self.c = [0] * (len(nums) + 1)
        for index, num in enumerate(nums):
            self.update(index, num)

    def update(self, index: int, val: int) -> None:
        diff = val - self.sumRange(index, index)
        pos = index + 1
        while pos < len(self.c):
            self.c[pos] += diff
            pos += pos & -pos

    def sumRange(self, left: int, right: int) -> int:
        return self.prevSum(right) - self.prevSum(left - 1)

    # 求前缀和
    def prevSum(self, index: int) -> int:
        pos = index + 1
        prev_sum = 0
        while pos > 0:
            prev_sum += self.c[pos]
            pos -= pos & -pos
        return prev_sum
