class Solution:
    def countVowelStrings(self, n: int) -> int:
        """
        https://leetcode.com/problems/count-sorted-vowel-strings/
        f(n) 与 f(n - 1) 之间的关系

        """
        sum_a, sum_e, sum_i, sum_o, sum_u = 5, 4, 3, 2, 1
        for i in range(1, n):
            sum_a = sum_a + sum_e + sum_i + sum_o + sum_u
            sum_e = sum_e + sum_i + sum_o + sum_u
            sum_i = sum_i + sum_o + sum_u
            sum_o = sum_o + sum_u
        return sum_a
