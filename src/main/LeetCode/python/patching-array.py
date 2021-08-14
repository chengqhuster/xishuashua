from typing import List


class Solution:
    def minPatches(self, nums: List[int], n: int) -> int:
        """
        https://leetcode.com/problems/patching-array/
        参考 maximum-number-of-consecutive-values-you-can-make

        """
        res, max_sum, pos = 0, 1, 0
        nums.sort()
        while max_sum <= n:
            if pos < len(nums) and nums[pos] <= max_sum:
                max_sum += nums[pos]
                pos += 1
            else:
                max_sum += max_sum
                res += 1

        return res
