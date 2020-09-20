package LeetCode.java;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目描述：https://leetcode.com/problems/simplify-path/
 *
 * 思路简述：使用栈数据结构
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] ss = path.split("/");
        for (String s : ss) {
            if (s != null && s.length() > 0) {
                if ("..".equals(s)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (!".".equals(s)) {
                    stack.push(s);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            String dic = stack.pollLast();
            sb.append("/").append(dic);
        }
        if (sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }
}
