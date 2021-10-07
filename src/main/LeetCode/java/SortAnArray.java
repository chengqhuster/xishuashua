package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/sort-an-array/
 *
 * 思路简述：简化版的 Array.sort，结合了插入排序，快速排序和归并排序
 */
public class SortAnArray {

    private static final int INSERTION_SORT_THRESHOLD = 47;
    private static final int MERGESORT_THRESHOLD = 286;


    public int[] sortArray(int[] nums) {
        // 临时数组，避免频繁创建数组
        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    private void sort(int[] num, int left, int right, int[] temp) {
        int gap = right - left;
        if (gap <= 0) {
            return;
        }
        if (gap < INSERTION_SORT_THRESHOLD) {
            // 插入排序（优于冒泡排序，不需要交换操作）
            for (int i = left, j = i; i < right; j = ++i) {
                int ai = num[i + 1];
                while (ai < num[j]) {
                    num[j + 1] = num[j];
                    if (j-- == left) {
                        break;
                    }
                }
                num[j + 1] = ai;
            }
        } else if (gap < MERGESORT_THRESHOLD) {
            // 快速排序，随机选择元素
            int idx = left + (int)(Math.random() * (right - left + 1));
            int pivot = num[idx];
            /*
             *   left part    center part              right part
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      great
             *
             * Invariants:
             *
             *   all in [left, less)   < pivot
             *   all in [less, k)     == pivot
             *   all in (great, right] > pivot
             */
            int less = left, great = right;
            for (int k = less; k <= great; k++) {
                if (num[k] == pivot) {
                    continue;
                }
                int ak = num[k];
                if (ak < pivot) {
                    num[k] = num[less];
                    num[less] = ak;
                    less++;
                } else {
                    while (num[great] > pivot) {
                        great--;
                    }
                    if (num[great] < pivot) {
                        num[k] = num[less];
                        num[less] = num[great];
                        less++;
                    } else {
                        // num[great] = pivot
                        num[k] = pivot;
                    }

                    num[great] = ak;
                    great--;
                }
            }
            sort(num, left, less - 1, temp);
            sort(num, great + 1, right, temp);
        } else {
            // 归并排序
            int mid = (left + right) >>> 1;
            sort(num, left, mid, temp);
            sort(num, mid + 1, right, temp);
            // merge
            int tempIdx = 0, leftPos = left, rightPos = mid + 1;
            while (leftPos <= mid && rightPos <= right) {
                if (num[leftPos] < num[rightPos]) {
                    temp[tempIdx++] = num[leftPos++];
                } else {
                    temp[tempIdx++] = num[rightPos++];
                }
            }
            while (leftPos <= mid) {
                temp[tempIdx++] = num[leftPos++];
            }
            while (rightPos <= right) {
                temp[tempIdx++] = num[rightPos++];
            }

            tempIdx = 0;
            while (left <= right){
                num[left++] = temp[tempIdx++];
            }
        }
    }
}
