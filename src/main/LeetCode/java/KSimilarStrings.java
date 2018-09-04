package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/k-similar-strings/description/
 *
 * 思路简述：DSF
 *          深度优先遍历和宽度优先遍历 两者都需要记录遍历时的状态信息，但
 *          DSF在遍历时记录的状态可能需要随着选择分支的改变进行撤销恢复操作
 *
 *          本题目用state数组记录字符串A的状态，用 1 表示对应位置的字符不需要
 *          再做变更了。
 *
 *          优化：耗时较长
 *
 *          需要数学证明：交换的顺序对最终的状态没有影响
 *
 */

public class KSimilarStrings {
    public int kSimilarity(String A, String B) {
        if(A == null || A.length() == 0 || B == null || B.length() == 0)
            return 0;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[] state = new int[A.length()];
        return dsf(a, b, state);
    }

    private int dsf(char[] a, char[] b, int[] state) {
        int deep = 0;
        int N = a.length;
        int index = 0;
        while(a[index] == b[index]) {
            if(index >= N-1)
                break;
            index++;
        }
        if(index == N-1){
            // a b 完全一致到达最底层 返回 0
            return deep;
        } else {
            // 寻找a的不一致处(index)需要匹配的目标位置
            for(int i=index+1; i<N; i++) {
                if(b[i] == a[index] && state[i] == 0) {
                    // 改变状态
                    state[i] = 1;
                    swap(a, i, index);
                    if(deep == 0) {
                        // 之前没有有效返回值
                        deep =dsf(a, b, state) + 1;
                    } else {
                        // 返回较小的结果
                        deep = Math.min(dsf(a, b, state) + 1, deep);
                    }
                    // 恢复状态
                    swap(a, i, index);
                    state[i] = 0;
                }
            }
            return deep;
        }
    }

    private void swap(char[] c, int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }
}
