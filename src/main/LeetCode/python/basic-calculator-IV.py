import collections
import functools
import itertools
from typing import List, Tuple


class Solution:
    def basicCalculatorIV(self, expression: str, evalvars: List[str], evalints: List[int]) -> List[str]:
        """
        https://leetcode.com/problems/basic-calculator-iv/
        先做变量替换，再通过后缀表达式求解运算，合并同类项

        """
        # 增加空号分割
        filled_ep = []
        for c in expression:
            if c == '(':
                filled_ep.append(c + ' ')
            elif c == ')':
                filled_ep.append(' ' + c)
            else:
                filled_ep.append(c)
        # 消除减号和负号
        symbols = ''.join(filled_ep).split()
        replace_map = {x: y for x, y in zip(evalvars, evalints)}
        # items 的元素 type、content、positive，依次是
        # 类型（* ( ) + num var）
        # 内容 123 or dfg
        # 正负性质 true or false
        items: List[Tuple[str, str, bool]] = []
        for symbol in symbols:
            if symbol == '*':
                items.append(('*', 'None', True))
            elif symbol == '(':
                items.append(('(', 'None', True))
            elif symbol == ')':
                items.append((')', 'None', True))
            elif symbol == '+':
                items.append(('+', 'None', True))
            elif symbol == '-':
                items.append(('+', 'None', True))
                items.append(('num', '1', False))
                items.append(('*', 'None', True))
            else:
                if '0' <= symbol[0] <= '9':
                    items.append(('num', symbol, True))
                else:
                    if symbol in replace_map:
                        items.append(('num', str(replace_map[symbol]), True))
                    else:
                        items.append(('var', symbol, True))

        # 构建后缀表达式
        post_index = []
        queue = collections.deque()
        for item in items:
            if item[0] == '(':
                queue.append(item)
            elif item[0] == ')':
                while queue[-1][0] != '(':
                    post_index.append(queue.pop())
                queue.pop()
            elif item[0] == '+':
                while queue and queue[-1][0] == '*':
                    post_index.append(queue.pop())
                queue.append(item)
            elif item[0] == '*':
                queue.append(item)
            else:
                post_index.append(item)
        while queue:
            post_index.append(queue.pop())

        # 合并同类项目
        def merge(item_list: List[Tuple[int, List[str], str]]) -> List[Tuple[int, List[str], str]]:
            if len(item_list) == 0:
                return item_list
            item_list.sort(key=lambda x: x[2])
            merge_list = []
            tag, co = item_list[0][2], 0
            for i in range(len(item_list)):
                if tag == item_list[i][2]:
                    co += item_list[i][0]
                else:
                    # 0 项去掉
                    if co != 0:
                        merge_list.append((co, item_list[i - 1][1], tag))
                    tag = item_list[i][2]
                    co = item_list[i][0]
            if co != 0:
                merge_list.append((co, item_list[-1][1], tag))
            return merge_list
        # 求解表达式
        # stack 存储表达式 list，list 的元素是 (系数, 变量集, 变量集字典序组合字符)
        stack = collections.deque()
        for item in post_index:
            if item[0] == '+':
                sum_list = stack.pop() + stack.pop()
                stack.append(merge(sum_list))
            elif item[0] == '*':
                a, b = stack.pop(), stack.pop()
                multi_list = []
                for m, n in itertools.product(a, b):
                    var_sort_list = sorted(m[1] + n[1])
                    multi_list.append((m[0] * n[0], var_sort_list, '*'.join(var_sort_list)))
                stack.append(merge(multi_list))

            else:
                if item[0] == 'num':
                    stack.append([(int(item[1]) * (1 if item[2] else -1), [], '')])
                else:
                    stack.append([(1 if item[2] else -1, [item[1]], item[1])])

        # merge 去掉常数 0
        out_list = merge(stack.pop())

        def out_sort(s1: Tuple[int, List[str], str], s2: Tuple[int, List[str], str]) -> int:
            if len(s1[1]) > len(s2[1]):
                return -1
            elif len(s1[1]) < len(s2[1]):
                return 1
            else:
                if s1[2] < s2[2]:
                    return -1
                elif s1[2] > s2[2]:
                    return 1
                else:
                    return 0
        res_list = sorted(out_list, key=functools.cmp_to_key(out_sort))

        return ['' + str(x[0]) + ('*' + x[2] if len(x[2]) > 0 else '') for x in res_list]
