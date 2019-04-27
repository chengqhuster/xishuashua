package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * 思路简述：1. 利用BST保存信息，Node 的 val 为关键字，sum 代表左子树的节点数（比val小的节点数量），dup 记录 val 重复的数量
 *             数组从后往前遍历，对数组的每个关键字做插入操作，preSum 用来计算结果值，
 *             往左子树遍历时 当前节点 sum 自增，preSum 不变
 *             往右子树遍历时 当前节点 sum 不变，preSum 加上当前节点的 sum（小于关键字的数量）与 dup（重复数）
 *
 *             这种方式用搜索树的形式减少计算量（树节点保存了小于节点关键字的数量信息），但树无法添加平衡条件（打破了数量关系）
 *             因此最坏的情况会变成 N^2
 *
 *           2. 归并排序 在归并过程中两个有序数组合成一个有序数组 rightCount 统计后半段已经选择的元素数量，若选中前半段元素，
 *           则后半段中比该元素小的元素数量即为 rightCount，即为符合题目要求的统计信息
 *           若是直接进行归并排序，则元素的原始索引位置信息丢失，归并过程中的 rightCount 信息无法累计到初始索引的位置上
 *           因此我们增加了 index 数组，index 数组记录的是排序过程中的元素在 nums 数组中的索引位置，也即 nums 元素不会改变
 *           只对 index 数组进行操作
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    int count[];

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        count = new int[nums.length];
        int [] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, index, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            res.add(count[i]);
        }
        return res;
    }

    private void mergeSort(int[] nums, int[] index, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, index, start, mid);
        mergeSort(nums, index, mid + 1, end);
        merge(nums, index, start, end);
    }

    private void merge(int[] nums, int[] index, int start, int end) {
        int mid = (start + end) / 2;
        int left = start;
        int right = mid + 1;
        int rightCount = 0;
        int sortIndex = 0;
        int[] newIndex = new int[end - start + 1];
        while (left <= mid && right <= end) {
            if (nums[index[right]] < nums[index[left]]) {
                newIndex[sortIndex] = index[right];
                rightCount++;
                right++;
            }  else {
                newIndex[sortIndex] = index[left];
                count[index[left]] += rightCount;
                left++;
            }
            sortIndex++;
        }
        while (left <= mid) {
            newIndex[sortIndex] = index[left];
            count[index[left]] += rightCount;
            left++;
            sortIndex++;
        }
        while (right <= end) {
            newIndex[sortIndex++] = index[right++];
        }
        for (int i = start; i <= end; i++) {
            index[i] = newIndex[i - start];
        }
    }

    public List<Integer> countSmallerBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }

    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            ans[i] = preSum;
            node =  new Node(num, 0);
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.sum + node.dup);
        }
        return node;
    }

    class Node {
        Node left, right;
        int val, sum, dup = 1;
        Node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }
}
