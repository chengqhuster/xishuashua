class MaximumBinaryStringAfterChange:
    def maximumBinaryString(self, binary: str) -> str:
        """
        https://leetcode.com/problems/maximum-binary-string-after-change/
        忽略前置'1', 统计'0'的数量即可

        :param binary:
        :return:
        """
        # 以下代码一行也可实现，但可读性差
        # head = binary.find('0')
        # if head == -1:
        #     return binary
        # else:
        #     count = binary.count('0')
        #     return '1' * (head + count - 1) + '0' + '1' * (len(binary) - head - count)

        # 更好的写法
        if '0' not in binary:
            return binary
        else:
            # 统计的是首位 0 后面 1 的数量
            count, length = binary.count('1', binary.find('0')), len(binary)
            return '1' * (length - count - 1) + '0' + '1' * count
