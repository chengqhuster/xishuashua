import string
from typing import List


class Solution:
    def suggestedProducts(self, products: List[str], searchWord: str) -> List[List[str]]:
        """
        https://leetcode.com/problems/search-suggestions-system/
        构造字典树

        """
        root = [False, {}]
        # 构建
        for product in products:
            node = root
            for index, a in enumerate(product):
                if a not in node[1]:
                    node[1][a] = [False, {}]
                node = node[1][a]
            node[0] = True

        # 查询
        res = []
        suggest = []

        def find_suggest_word(head, s_list):
            # 用栈模拟递归
            stack = [[head, 0]]
            while stack:
                if len(suggest) == 3:
                    while len(stack) > 1:
                        stack.pop()
                        s_list.pop()
                    return
                peek = stack[-1]
                for pos, c in enumerate(string.ascii_lowercase[peek[1]:]):
                    if c in peek[0][1]:
                        s_list.append(c)
                        if peek[0][1][c][0]:
                            suggest.append("".join(s_list))
                        peek[1] += pos + 1
                        stack.append([peek[0][1][c], 0])
                        break
                else:
                    stack.pop()
                    s_list.pop()

        node = root
        for index, b in enumerate(searchWord):
            suggest.clear()
            if b in node[1]:
                node = node[1][b]
                if node[0]:
                    suggest.append(searchWord[:index + 1])
                find_suggest_word(node, list(searchWord[:index + 1]))
                res.append(list(suggest))
            else:
                res.extend([list(suggest) for _ in range(len(searchWord) - index)])
                break
        return res
