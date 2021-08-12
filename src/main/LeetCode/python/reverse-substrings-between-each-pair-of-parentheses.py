class Solution:
    def reverseParentheses(self, s: str) -> str:
        """
        https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
        从右括号入手

        """
        while True:
            right = s.find(')')
            if right == -1:
                break
            else:
                left = s.rfind('(', 0, right)
                s = s[:left] + s[right - 1:left:-1] + s[right + 1:]

        return s
