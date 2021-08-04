
class LongestSubstringWithAtLeastKRepeatingCharacters:

    def longestSubstring(self, s: str, k: int) -> int:
        """
        https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
        统计出现的字母的数量，小于要求数量的字母用于字符串分片
        利用小写字母总数26个降低复杂度

        """
        for c in set(s):
            if s.count(c) < k:
                return max(self.longestSubstring(x, k) for x in s.split(c))
        return len(s)

    def longestSubstring_2(self, s: str, k: int) -> int:
        """
        利用小写字母总数26个降低复杂度

        """

        res = 0
        for exp_uni_count in range(1, 27):
            cur_uni_count, no_less_k, head, tail = 0, 0, 0, 0
            alpha_map = {}
            while tail < len(s):
                if cur_uni_count <= exp_uni_count:
                    if s[tail] not in alpha_map:
                        alpha_map[s[tail]] = 0
                        cur_uni_count += 1
                    alpha_map[s[tail]] += 1
                    if alpha_map[s[tail]] == k:
                        no_less_k += 1
                    tail += 1
                else:
                    if alpha_map[s[head]] == k:
                        no_less_k -= 1
                    alpha_map[s[head]] -= 1
                    if alpha_map[s[head]] == 0:
                        del alpha_map[s[head]]
                        cur_uni_count -= 1
                    head += 1

                if cur_uni_count == exp_uni_count & no_less_k == exp_uni_count:
                    res = max(res, tail - head)

        return res
