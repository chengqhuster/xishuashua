class Solution:
    def computeArea(self, ax1: int, ay1: int, ax2: int, ay2: int, bx1: int, by1: int, bx2: int, by2: int) -> int:
        """
        https://leetcode.com/problems/rectangle-area/
        两个矩形面积减去相交部分的面积

        """
        def square_are(x1: int, y1: int, x2: int, y2: int) -> int:
            return abs(x1 - x2) * abs(y1 - y2)

        def interval_len(a1: int, a2: int, b1: int, b2: int) -> int:
            if max(a1, a2) <= min(b1, b2) or min(a1, a2) >= max(b1, b2):
                return 0
            else:
                m = sorted([a1, a2, b1, b2])
                return m[2] - m[1]
        interval_area = interval_len(ax1, ax2, bx1, bx2) * interval_len(ay1, ay2, by1, by2)

        return square_are(ax1, ay1, ax2, ay2) + square_are(bx1, by1, bx2, by2) - interval_area
