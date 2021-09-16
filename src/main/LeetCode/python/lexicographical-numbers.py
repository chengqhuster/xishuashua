from typing import List


class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        """
        https://leetcode.com/problems/lexicographical-numbers/
        自短向长拼接

        """
        num_len = len(str(n))
        num_list = []

        def form_num(s: str) -> None:
            if len(s) > num_len or int(s) > n:
                return
            num_list.append(s)
            for i in range(10):
                s_next = s + str(i)
                if int(s_next) > n:
                    break
                form_num(s_next)
        for leading_num in range(1, 10):
            form_num(str(leading_num))
        return num_list
