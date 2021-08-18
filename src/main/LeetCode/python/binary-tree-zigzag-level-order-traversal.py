from typing import Optional, List
from collections import deque


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        """
        https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
        BSF

        """
        if root is None:
            return []
        dq, res = deque([root]), []
        head_right_flag = True
        while dq:
            cur = []
            for _ in range(len(dq)):
                node = dq.popleft()
                cur.append(node.val)
                if node.left is not None:
                    dq.append(node.left)
                if node.right is not None:
                    dq.append(node.right)
            if head_right_flag:
                res.append(cur)
                head_right_flag = False
            else:
                res.append(reversed(cur))
                head_right_flag = True

        return res
