package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/boats-to-save-people/
 *
 * 思路简述：首尾指针求解，特殊的背包问题
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0, p = 0, q = people.length - 1;
        while (p <= q) {
            if (people[p] + people[q] <= limit) {
                p++;
            }
            q--;
            res++;
        }
        return res;
    }
}
