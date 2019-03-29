package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/relative-ranks/
 *
 * 思路简述：维护好排序后的数据与排序前索引的映射关系，使用 map 或者 内部类均可
 *
 */


import java.util.Arrays;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        ScoreInfo[] infos = new ScoreInfo[nums.length];
        for (int i = 0; i < nums.length; i++) {
            infos[i] = new ScoreInfo(i, nums[i]);
        }
        Arrays.sort(infos, (o1, o2) -> o2.score - o1.score);
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = infos[i].index;
            if (i == 0) {
                res[index] = "Gold Medal";
            } else if (i == 1) {
                res[index] = "Silver Medal";
            } else if (i == 2) {
                res[index] = "Bronze Medal";
            } else {
                res[index] = "" + (i + 1);
            }
        }
        return res;
    }

    class ScoreInfo {
        int index;
        int score;
        public ScoreInfo(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }
}
