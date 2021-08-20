from typing import List


class Solution:
    def shiftingLetters(self, s: str, shifts: List[int]) -> str:
        """
        https://leetcode.com/problems/shifting-letters/
        逆序求前缀和，注意取模

        """
        for i in range(len(shifts), 1, -1):
            shifts[i - 2] = (shifts[i - 1] + shifts[i - 2]) % 26
        by = bytearray(s.encode())
        a_int = ord('a')
        for index, i in enumerate(shifts):
            by[index] = (by[index] - a_int + i) % 26 + a_int
        return str(by.decode())
