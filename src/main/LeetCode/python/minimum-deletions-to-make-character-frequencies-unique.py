class Solution:
    def minDeletions(self, s: str) -> int:
        """
        https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
        统计不同字母数量即可

        """
        alphas = set(s)
        alpha_count = [(a, s.count(a)) for a in alphas]
        alpha_count.sort(key=lambda b: b[1], reverse=True)
        allow_min = alpha_count[0][1]
        del_count = 0
        for item in alpha_count:
            del_count += 0 if item[1] <= allow_min else item[1] - allow_min
            allow_min = 0 if allow_min == 0 else min(item[1] - 1, allow_min - 1)
        return del_count
