package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/find-median-from-data-stream/
 *
 * 思路简述：中位数将数据分成左右长度相等的两部分（详细参考）
 *          https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
 *
 */

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int lo = 0;
        int hi = m;
        int halfLen = (m + n + 1)/2;
        while (lo <= hi) {
            int i = (lo + hi)/2;
            int j = halfLen - i;
            // i > 0 =====> j < n
            if (i > 0 && nums1[i - 1] > nums2[j]) {
                hi = i - 1;
            }
            // i < m =====> j > 0
            else if (i < m && nums1[i] < nums2[j - 1]) {
                lo = i  + 1;
            } else {
                double max_left;
                if (i == 0) {
                    max_left = nums2[j - 1];
                } else if (j == 0) {
                    max_left = nums1[i - 1];
                } else {
                    max_left = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((n + m)%2 == 1) {
                    return max_left;
                }
                double min_right;
                if (i == m) {
                    min_right = nums2[j];
                } else if (j == n) {
                    min_right = nums1[i];
                } else {
                    min_right = Math.min(nums1[i], nums2[j]);
                }
                return (max_left + min_right)/2;
            }
        }
        // cannot reach here, should throw exception (invalid input)
        return 0;
    }
}
