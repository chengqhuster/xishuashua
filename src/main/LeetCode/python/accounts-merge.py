from typing import List, Tuple


class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        """
        https://leetcode.com/problems/accounts-merge/
        合并账户，邮箱作为唯一标识
        """
        map_union = {x: x for account in accounts for index, x in enumerate(account) if index != 0}
        email_map = {x: account[0] for account in accounts for index, x in enumerate(account) if index != 0}

        def find(a: str) -> Tuple[str, int]:
            dep = 0
            while map_union[a] != a:
                map_union[a] = map_union[map_union[a]]
                dep += 1
                a = map_union[a]
            return a, dep

        def merge(a: str, b: str) -> None:
            a_res, b_res = find(a), find(b)
            if a_res[0] != b_res[0]:
                if a_res[1] > b_res[1]:
                    map_union[b_res[0]] = a_res[0]
                else:
                    map_union[a_res[0]] = b_res[0]

        for account in accounts:
            for pos in range(2, len(account)):
                merge(account[1], account[pos])

        email_group_map = {}
        for key in map_union.keys():
            root, _ = find(key)
            if root not in email_group_map:
                email_group_map[root] = []
            email_group_map[root].append(key)

        return [[email_map[k]] + sorted(v) for k, v in email_group_map.items()]
