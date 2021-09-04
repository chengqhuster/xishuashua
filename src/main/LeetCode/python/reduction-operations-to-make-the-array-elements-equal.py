from typing import List


class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        """
        https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/
        排序，统计

        """
        nums.sort()
        count, res = 0, 0
        for i in range(1, len(nums)):
            if nums[i] != nums[i - 1]:
                count += 1
            res += count

        return res

