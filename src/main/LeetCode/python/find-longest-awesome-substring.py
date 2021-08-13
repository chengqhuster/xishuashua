import string


class Solution:

    def longestAwesome(self, s: str) -> int:
        """
         https://leetcode.com/problems/find-longest-awesome-substring/
         相同的还是前缀的思想，但不用统计数量，只需要通过异或确定奇偶性
         另外用一个数组保存某一系列奇偶组合最早出现的位置

        """
        flag, res = 0, 0
        # 注意初始化条件，store[0]最小的可以是位置为-1的空字符串
        store = [-1] + [len(s)] * 1024
        for index, a in enumerate(s):
            flag ^= (1 << int(a))
            store[flag] = min(store[flag], index)
            for b in range(10):
                res = max(res, index - store[flag ^ (1 << b)])
            res = max(res, index - store[flag])

        return res

    def longest_awesome_tle(self, s: str) -> int:
        """
        https://leetcode.com/problems/find-longest-awesome-substring/
        统计处前置n位不同数字的数量，二维遍历的时候加快判断速度，本质上还是O(n^2)，算法超时

        """
        alpha_map = {x: [0] * (len(s) + 1) for x in string.digits}
        for index, a in enumerate(s):
            for b in string.digits:
                alpha_map[b][index + 1] = alpha_map[b][index] + (1 if a == b else 0)

        res = 0
        for a in range(len(s)):
            for b in range(a, len(s)):
                odd_diff = 0
                for c in string.digits:
                    odd_diff += 1 if ((alpha_map[c][b + 1] - alpha_map[c][a]) % 2 == 1) else 0
                    if odd_diff > 1:
                        break
                if odd_diff <= 1:
                    res = max(res, b - a + 1)

        return res
