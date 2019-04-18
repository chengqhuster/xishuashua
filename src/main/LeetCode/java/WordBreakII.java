package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/word-break-ii/
 *
 * 思路简述：参考 WordBreak，dfs时候记录子问题的解
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }
        res = dfs(s, wordDict, new HashMap<>());
        return res;
    }

    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> mem) {
        if (mem.containsKey(s)) {
            return mem.get(s);
        }
        List<String> solve = new ArrayList<>();
        if (s.length() == 0) {
            solve.add("");
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subSolve = dfs(s.substring(word.length()), wordDict, mem);
                for (String sub : subSolve) {
                    solve.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        mem.put(s, solve);
        return solve;
    }
}
