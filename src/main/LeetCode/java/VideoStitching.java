package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/video-stitching/
 *
 * 思路简述：1. 贪心算法，clips优先左界升序再右界降序排列，每次找最右界的下一个点
 */

import java.util.Arrays;

public class VideoStitching {
    public int videoStitching(int[][] clips, int T) {
        if (clips == null || clips.length == 0 || T < 0) {
            return -1;
        }
        Arrays.sort(clips, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });
        if (clips[0][0] != 0) {
            return -1;
        }
        int count = 1, index = 1, maxNext = clips[0][1];
        while (index < clips.length && maxNext < T) {
            int temp = maxNext;
            while (index < clips.length && clips[index][0] <= maxNext) {
                temp = Math.max(temp, clips[index][1]);
                index++;
            }
            if (temp == maxNext) {
                break;
            } else {
                maxNext = temp;
                count++;
            }
        }
        if (maxNext < T) {
            return -1;
        } else {
            return count;
        }
    }
}
