class Solution:
    def countVowelStrings(self, n: int) -> int:
        """
        https://leetcode.com/problems/count-sorted-vowel-strings/
        f(n) 与 f(n - 1) 之间的关系

        """
        sum_list = [1] * 5
        for i in range(0, n):
            sum_list[4] = sum(sum_list[:4])
            sum_list[3] = sum(sum_list[:3])
            sum_list[2] = sum(sum_list[:2])
            sum_list[1] = sum(sum_list[:1])
        return sum_list[4]
