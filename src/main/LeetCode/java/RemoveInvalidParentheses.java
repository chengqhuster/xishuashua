package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 * 思路简述：DSF
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveInvalidParentheses {
    // 被移除的数量
    private int removedCharacters = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> res = new HashSet<>();
        if(s == null || s.length() == 0) {
            res.add("");
            return setToList(res);
        }
        // 记录状态 初始 0 被移除 1 被保留 2
        int[] state = new int[s.length()];
        dsf(s, state, res);
        return setToList(res);
    }

    private List<String> setToList(HashSet<String> res) {
        List<String> result = new ArrayList<>();
        for(String s : res) {
            result.add(s);
        }
        return result;
    }

    private void dsf(String s, int[] state, HashSet<String> res) {
        int index = 0;
        for(; index<state.length; index++) {
            if(state[index] == 0)
                break;
        }
        if(index == state.length) {
            // 处理完所有字符
            int count = 0;
            for(int i=0; i<state.length; i++) {
                if(state[i] == 1) {
                    count++;
                }
            }
            if(count < removedCharacters) {
                res.clear();
                addString(res, s, state);
                removedCharacters = count;
            } else if(count == removedCharacters) {
                addString(res, s, state);
            }
        } else {
            int[] temp = new int[state.length];
            System.arraycopy(state, 0, temp, 0, state.length);
            if(s.charAt(index) == ')') {
                // 只能丢弃
                state[index] = 1;
                dsf(s, state, res);
            } else if(s.charAt(index) != '(') {
                //保留字母
                state[index] = 2;
                dsf(s, state, res);
            } else {
                // 处理 '(' 分支  多条路径时候一定要记得恢复分支状态
                // 1.寻找匹配 ')'
                for(int i=index+1; i<state.length; i++) {
                    if(state[i] ==0 && s.charAt(i) == ')') {
                        state[index] = 2;
                        state[i] = 2;
                        dsf(s, state, res);
                        // 恢复状态!!!
                        state[index] = 0;
                        state[i] = 0;
                    }
                }
                // 2.丢弃
                state[index] = 1;
                dsf(s, state, res);
            }
            System.arraycopy(temp, 0, state, 0, state.length);
        }
    }

    private void addString(HashSet<String> res, String s, int[] state) {
        StringBuilder validString = new StringBuilder();
        for(int i=0; i<state.length; i++) {
            if(state[i] == 2) {
                validString.append(s.charAt(i));
            }
        }
        res.add(validString.toString());
    }
}
