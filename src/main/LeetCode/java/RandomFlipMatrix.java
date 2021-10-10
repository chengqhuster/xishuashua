package LeetCode.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 题目描述：https://leetcode.com/problems/random-flip-matrix/
 *
 * 思路简述：1. 直接构造数组，超出内存限制
 *         2. 每次将取出的数放到序列最后面
 *
 */
public class RandomFlipMatrix {

    private final Random random = new Random();

    private final int m, n;

    private int total;

    private final Map<Integer, Integer> map = new HashMap<>();

    public RandomFlipMatrix(int m, int n) {
        this.m  = m;
        this.n = n;
        this.total = m * n;
    }

    public int[] flip() {
        int next = random.nextInt(total--);
        int x = map.getOrDefault(next, next);
        map.put(next, map.getOrDefault(total, total));
        map.put(total, x);
        return new int[]{x / n, x % n};
    }

    public void reset() {
        total = m * n;
    }
}
