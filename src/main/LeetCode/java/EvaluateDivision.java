package LeetCode.java;

//TODO fix this wrong solution
/*
 * 题目描述：https://leetcode.com/problems/evaluate-division/
 *
 * 思路简述：
 *
 */

import java.util.HashMap;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Integer> map = new HashMap<>();
        int num = 0;
        for (String[] ss : equations) {
            for (String s : ss) {
                if (!map.containsKey(s)) {
                    map.put(s, num);
                    num++;
                }
            }
        }
        int[] id = new int[num];
        double[] state = new double[num];
        boolean[] flag = new boolean[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
        for (int i = 0; i < equations.length; i++) {
            int aId = map.get(equations[i][0]);
            int bId = map.get(equations[i][1]);
            if (id[aId] == id[bId]) {
                continue;
            }
            if (aId == id[aId] && bId == id[bId]) {
                if (!flag[aId]) {
                    id[aId] = bId;
                    state[bId] = 1;
                    state[aId] = values[i];
                } else {
                    id[bId] = aId;
                    state[aId] = 1;
                    state[bId] = 1/values[i];
                }
                flag[aId] = true;
                flag[bId] = true;

            } else if (aId == id[aId]) {
                id[aId] = bId;
                state[aId] = state[bId]*values[i];
                flag[aId] = true;
            } else if (bId == id[bId]) {
                id[bId] = aId;
                state[bId] = state[aId]/values[i];
                flag[bId] = true;
            } else {
                for (int j = 0; j < num; j++) {
                    int last = id[aId];
                    double p = state[aId];
                    if (id[j] == last) {
                        id[j] = id[bId];
                        state[j] = state[j]/p*values[i]*state[bId];
                    }
                }
            }
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!map.containsKey(queries[i][0]) || !map.containsKey(queries[i][1])) {
                res[i] = -1.0;
                continue;
            }
            int aId = map.get(queries[i][0]);
            int bId = map.get(queries[i][1]);
            if (id[aId] != id[bId]) {
                res[i] = -1.0;
            } else {
                res[i] = state[aId]/state[bId];
            }
        }
        return res;
    }
}
