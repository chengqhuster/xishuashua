class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        """
        https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
        参考 find-longest-awesome-substring

        """
        # 思考store[0]初始化为什么是-1而不是0，store的含义是有相同奇偶性组合的最小前缀index(包含)位置，
        # 0位置处的奇偶性组合显然不一定为0，因此是再往前一个位置，-1位置
        vowels, flag, res, store = 'aeiou', 0, 0, [-1] + [len(s)] * 2**5
        for index, a in enumerate(s):
            if a in vowels:
                flag ^= 1 << vowels.index(a)
            store[flag] = min(store[flag], index)
            res = max(res, index - store[flag])

        return res
