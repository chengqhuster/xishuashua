import bisect
from typing import List


class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        """
        https://leetcode.com/problems/minimum-absolute-sum-difference/
        给其中一个数组排序，一次求每个位置的diff以及最大的diff减少量

        """
        nums1_sort = sorted(nums1)
        res, max_reduce = 0, 0
        for a, b in zip(nums1, nums2):
            diff = abs(a - b)
            res += diff
            pos = bisect.bisect(nums1_sort, b)
            if pos > 0:
                max_reduce = max(max_reduce, diff - abs(nums1_sort[pos - 1] - b))
            if pos < len(nums1):
                max_reduce = max(max_reduce, diff - abs(nums1_sort[pos] - b))
        return (res - max_reduce) % 1000000007
