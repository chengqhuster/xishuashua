import bisect
from typing import List


class Solution:
    def minOperations(self, nums: List[int]) -> int:
        """
        https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/
        排序，二分查找

        """
        size, res = len(nums), 0
        unique_sort_nums = sorted(set(nums))
        for index, num in enumerate(unique_sort_nums):
            target = bisect.bisect_left(unique_sort_nums, num + size)
            res = max(res, target - index)
        return size - res
