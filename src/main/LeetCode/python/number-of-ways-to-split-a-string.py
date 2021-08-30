class Solution:
    def numWays(self, s: str) -> int:
        """
        https://leetcode.com/problems/number-of-ways-to-split-a-string/
        统计中间分割的 0 串的长度

        """
        if len(s) < 3:
            return 0
        one_count = s.count('1')
        if one_count == 0:
            return ((len(s) - 1) * (len(s) - 2) // 2) % (pow(10, 9) + 7)
        if one_count % 3 != 0:
            return 0
        patch_count = int(one_count / 3)
        a, b, res = 0, 0, 1
        for c in s:
            if c == '0':
                a += 1
            else:
                b += 1
                if b == patch_count + 1 or b == patch_count * 2 + 1:
                    res *= a + 1
                a = 0

        return res % (pow(10, 9) + 7)
