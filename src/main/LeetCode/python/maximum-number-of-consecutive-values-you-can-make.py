from typing import List


class Solution:
    def getMaximumConsecutive(self, coins: List[int]) -> int:
        """
        https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/
        先排序

        """
        res = 1
        coins.sort()
        for coin in coins:
            if coin > res:
                return res
            else:
                res += coin

        return res
