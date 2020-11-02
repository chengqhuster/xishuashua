package LeetCode.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/stickers-to-spell-word/
 *
 * 思路简述：深度遍历 + 记忆
 */
public class StickersToSpellWord {

    private static final int LOW_CASE_LETTER_COUNT = 26;

    public int minStickers(String[] stickers, String target) {
        int[][] stickersCount = new int[stickers.length][];
        for (int i = 0; i < stickers.length; i++) {
            stickersCount[i] = toCountArray(stickers[i]);
        }
        Map<String, Integer> memory = new HashMap<>();
        memory.put("", 0);
        return dfsCore(target, stickersCount, memory);
    }

    private int dfsCore(String target, int[][] stickersCount, Map<String, Integer> memory) {
        if (memory.containsKey(target)) {
            return memory.get(target);
        }
        int res = -1;
        for (int[] stickerCount : stickersCount) {
            if (stickerCount[target.charAt(0) - 'a'] > 0) {
                int temp = dfsCore(getLeftTarget(target, Arrays.copyOf(stickerCount, LOW_CASE_LETTER_COUNT)),
                        stickersCount, memory);
                if (temp != -1) {
                    res = res == -1 ? temp + 1 : Math.min(res, temp + 1);
                }
            }
        }
        memory.put(target, res);
        return res;
    }

    private String getLeftTarget(String target, int[] stickerCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if (stickerCount[target.charAt(i) - 'a'] > 0) {
                stickerCount[target.charAt(i) - 'a']--;
            } else {
                stringBuilder.append(target.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    private int[] toCountArray(String str) {
        int[] count = new int[LOW_CASE_LETTER_COUNT];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        return count;
    }
}
