import collections
from typing import List, Dict, Set


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        """
        https://leetcode.com/problems/word-ladder/
        指定起始点的图最短路径

        """
        if endWord not in wordList:
            return 0
        if beginWord not in wordList:
            wordList.append(beginWord)
        sub_word_map: Dict[str, Set[str]] = {}
        # 将 word 某一位置的字符替换为 _ , 然后相同的归约到一起
        word_len = len(beginWord)
        for word in wordList:
            for i in range(word_len):
                sub_word = word[:i] + '_' + word[i + 1:]
                if sub_word not in sub_word_map:
                    sub_word_map[sub_word] = set()
                sub_word_map[sub_word].add(word)
        # 构建图
        word_graph: Dict[str, Set[str]] = {word: set() for word in wordList}
        for v in sub_word_map.values():
            for w in v:
                word_graph[w].update(v - {w})
        # BSF
        queue = collections.deque()
        queue.append(beginWord)
        visited: Set[str] = {beginWord}
        count = 1
        while queue:
            count += 1
            for i in range(len(queue)):
                word = queue.popleft()
                queue.extend(word_graph[word] - visited)
                visited.update(word_graph[word])
                if endWord in visited:
                    return count
        return 0
