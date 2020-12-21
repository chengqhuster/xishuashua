package LeetCode.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 题目描述：https://leetcode.com/problems/random-pick-with-blacklist/
 *
 * 思路简述：将黑名单映射到有效的数字上
 */
public class RandomPickWithBlacklist {
    private int m;
    private Map<Integer, Integer> map;
    private Random r;


    public RandomPickWithBlacklist(int N, int[] blacklist) {
        map = new HashMap<>();
        for (int blackListNum : blacklist) {
            map.put(blackListNum, 0);
        }
        m = N - blacklist.length;
        for (int blackListNum : blacklist) {
            if (blackListNum < m) {
                while (map.containsKey(N - 1)) {
                    N--;
                }
                map.put(blackListNum, N - 1);
                N--;
            }
        }
        r = new Random();
    }

    public int pick() {
        int randomInt = r.nextInt(m);
        if (map.containsKey(randomInt)) {
            return map.get(randomInt);
        }
        return randomInt;
    }
}
