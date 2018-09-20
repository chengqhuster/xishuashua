package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/first-missing-positive/
 *
 * 思路简述：从1开始的连续正数集合最大值不超过数组的大小，使用额外的数组(O(n))，存放1-n的值，最后从1开始没有出现的数即为所求
 *         题目要求常量的空间，可以使用交换的方式，在原数组上把1-n范围内的数放到对应索引的位置上
 *
 */

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 1;
        }
        int pos = 0;
        int N = nums.length;
        while(pos < N) {
            int last = N + 1;
            while(nums[pos] != last && nums[pos] >= 1 && nums[pos] <= N && nums[pos] != pos+1) {
                int temp = nums[pos];
                nums[pos] = nums[nums[pos] - 1];
                nums[temp - 1] = temp;
                last = temp;
            }
            pos++;
        }
        int res = 0;
        for(; res<nums.length; res++) {
            if(nums[res] != res + 1) {
                break;
            }
        }
        return res + 1;
//        int[] state = new int[nums.length + 1];
//        for(int i=0; i<nums.length; i++) {
//            if(nums[i] <= 0 || nums[i] > nums.length) {
//                continue;
//            }
//            state[nums[i]] = 1;
//        }
//        int index = 1;
//        for(; index<state.length; index++) {
//            if(state[index] == 0) {
//                break;
//            }
//        }
//        return index;
    }
}
