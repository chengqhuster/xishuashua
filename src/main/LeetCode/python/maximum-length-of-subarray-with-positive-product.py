from typing import List


class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        """
        https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
        0 做列表分割，再判断整体正负性，根据正负性决定是否需要剔除一个负数

        """
        res, pos = 0, 0
        while pos < len(nums):
            if 0 in nums[pos:]:
                zero_index = nums.index(0, pos)
            else:
                zero_index = len(nums)
            neg_count = sum(x < 0 for x in nums[pos:zero_index])
            if neg_count % 2 == 0:
                res = max(res, zero_index - pos)
            else:
                for i in range(zero_index - pos):
                    if nums[pos + i] < 0 or nums[zero_index - 1 - i] < 0:
                        res = max(res, zero_index - pos - 1 - i)
                        break
            pos = zero_index + 1
        return res
