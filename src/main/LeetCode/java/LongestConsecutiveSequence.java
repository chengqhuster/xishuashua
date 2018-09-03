package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * 思路简述：并查集 重点在于Union操作上面，相关的字符串类型的题目一般是取出两个字符串然后判断
 *         是否需要Union，本题要求的是连续整数，故而判断所求整数的相邻整数是否存在，在进行Union
 *         会更有效率。
 *
 */

import java.util.HashMap;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // Union Find Data Structure
        int[] id = new int[N];
        for(int i=0; i<N; i++) {
            id[i] = i;
        }
        // Length(store state)
        int[] state = new int[N];
        for(int i=0; i<N; i++) {
            state[i] = 1;
        }
        // Longest consecutive sequence's length
        int res = 1;
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for(int i=0; i<N; i++) {
            numMap.put(nums[i], i);
        }
        for(int num : nums) {
            if(numMap.containsKey(num - 1)) {
                res = judgeAndUnion(id, state, numMap.get(num), numMap.get(num - 1), res);
            }
            if(numMap.containsKey(num + 1)) {
                res = judgeAndUnion(id, state, numMap.get(num), numMap.get(num + 1), res);
            }
        }
        return res;
    }

    private int judgeAndUnion(int[] id ,int[] state, int a, int b, int length) {
        int aID = find(id, a);
        int bID = find(id, b);
        if(aID != bID) {
            // 优化 将较小的树作为子树加入较大树的根节点 使树结构更加平衡(需要额外的空间存储树深度，或在find时返回该信息)
            id[bID] = aID;
            state[aID] = state[aID] + state[bID];
            return Math.max(length, state[aID]);
        } else {
            return length;
        }
    }

    private int find(int[] id, int num) {
        // reduce tree deep
        while(id[num] != num) {
            id[num] = id[id[num]];
            num = id[num];
        }
        return num;
//        if(id[num] != num) {
//            return find(id, id[num]);
//        } else {
//            return num;
//        }
    }
}
