class RemoveAllOccurrencesOfASubstring:
    def removeOccurrences(self, s: str, part: str) -> str:
        """
        https://leetcode.com/problems/remove-all-occurrences-of-a-substring/

        """
        # 栈，存储可能符合条件的序列，元素是(s_index, p_index)
        stack = []
        # 子序列对应的 s 索引集合
        remove_index = set()
        # 按照 s 的索引遍历
        index = 0
        while index < len(s):
            # 已经探寻到的子序列索引，直接跳过
            if index in remove_index:
                index += 1
                continue
            # 取出 s 当前索引位置的元素 c
            c = s[index]
            if stack:
                # c 是栈最后一个序列的下一元素
                if c == part[stack[-1][0] + 1]:
                    stack.append((stack[-1][0] + 1, index))
                # 或者是子序列的第一个元素
                elif c == part[0]:
                    stack.append((0, index))
                # 都不满足的话
                else:
                    # 可能需要尝试回退，重新调整子序列起始位置
                    while stack:
                        p_index, s_index = stack.pop()
                        # p_index 从子序列的中间位置调整为起始位置
                        if (p_index > 0) & (part[p_index] == part[0]):
                            stack.append((0, s_index))
                            # 重置遍历的 index 位置
                            index = s_index
                            break
            # 栈为空
            else:
                # 建立子序列起始位置
                if c == part[0]:
                    stack.append((0, index))

            # 栈非空
            if stack:
                # 搜索到完整子序列
                if stack[-1][0] == len(part) - 1:
                    # 添加进子序列索引集合
                    remove_index.update(set([item[1] for item in stack[-len(part):]]))
                    # 去除相应栈元素
                    stack[-len(part):] = []

            # 探寻 s 下一个索引位置
            index += 1

        # 排除子序列对应的的 s 索引集合位置的元素
        res = []
        for index, c in enumerate(s):
            if index not in remove_index:
                res.append(c)
        # 建立最终的字符串
        return "".join(res)
