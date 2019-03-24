package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/satisfiability-of-equality-equations/
 *
 * 思路简述：并查集
 *
 */

import LeetCode.java.DataStruct.UnionAndFind;

public class SatisfiabilityOfEqualityEquations {
    public boolean equationsPossible(String[] equations) {
        if (equations == null) {
            return true;
        }
        UnionAndFind uf = new UnionAndFind();
        uf.reset(26);
        for (int i = 0; i < equations.length; i++) {
            if (equations[i].charAt(1) == '=') {
                uf.union(equations[i].charAt(0) - 'a', equations[i].charAt(3) - 'a');
            }
        }
        for (int i = 0; i < equations.length; i++) {
            if (equations[i].charAt(1) == '!') {
                if (uf.find(equations[i].charAt(0) - 'a') == uf.find(equations[i].charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
