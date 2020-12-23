package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * 思路简述：数组链表记录字符串结构
 *
 * TODO: 时间上需要优化
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    public String removeDuplicates(String s, int k) {
        // 长度比 s 多 1, 0 位置是伪头节点
        int[] next = new int[s.length() + 1];
        for (int i = 0; i < next.length; i++) {
            next[i] = i + 1;
        }
        int count;
        do {
            count = 0;
            for (int p = 0; p <= s.length();) {
                int mark = p, length = 0;
                // 检查接下来 k 个字符是否相同
                while (length < k - 1) {
                    if (next[mark] <= s.length() && next[next[mark]] <= s.length()
                            && s.charAt(next[mark] - 1) == s.charAt(next[next[mark]] - 1)) {
                        length++;
                        mark = next[mark];
                    } else {
                        break;
                    }
                }
                if (length == k - 1) {
                    // 相同的时候更新链表
                    count++;
                    next[p] = next[next[mark]];
                } else {
                    // 不同的时候更新指针位置
                    p = next[p];
                }
            }
        } while (count > 0);
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (next[start] <= s.length()) {
            sb.append(s.charAt(next[start] - 1));
            start = next[start];
        }
        return sb.toString();
    }
}
