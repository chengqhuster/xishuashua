package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/product-of-the-last-k-numbers/
 *
 * 思路简述：任何子序列的乘积都不会超过 2^32, 意味着任何不包含 0 的子序列，非 1 的元素个数不超过32个
 * 定义特殊二维数组 a[32][2], 其中 a[0][0] 是 0 元素后的第一个元素, a[i][1] 表示把 1 元素压缩后的统计数量
 * eg.    0 1 1 2 3 4 1 1 5 1 1 6 7 8 9 用二维数组表示为
 * a[i][0]  1   2 3 4     5     6 7 8 9
 * a[i][1]  2   1 1 3     3     1 1 1 1
 *
 * 标准思路：list维护不包含 0 元素的子序列的前缀乘积
 * 则 get(k) = k < list.size ? list.get(size - 1) / list.get(size - k - 1) : 0
 */
public class ProductOfTheLastKNumbers {

    private int length = 34;

    private int[][] noZeroOneCount = new int[length][2];

    private int tailIndex = 0;

    public ProductOfTheLastKNumbers() {

    }

    public void add(int num) {
        if (num == 0) {
            // 重置统计数组
            tailIndex = 0;
            noZeroOneCount[tailIndex][0] = 0;
        } else {
            if (noZeroOneCount[tailIndex][0] == 0) {
                // 初始化统计数组头节点
                noZeroOneCount[tailIndex] = new int[]{num, 1};
            } else {
                if (num == 1) {
                    // 压缩元素 1
                    noZeroOneCount[tailIndex][1]++;
                } else {
                    // 新增统计数组尾节点
                    tailIndex++;
                    noZeroOneCount[tailIndex] = new int[]{num, 1};
                }
            }
        }
    }

    public int getProduct(int k) {
        if (noZeroOneCount[0][0] == 0) {
            return 0;
        }
        int backLen = 0, product = 1;
        for (int index = tailIndex; index >= 0; index--) {
            backLen += noZeroOneCount[index][1];
            if (backLen > k) {
                return product;
            }
            product *= noZeroOneCount[index][0];
            if (backLen == k) {
                return product;
            }
        }

        return 0;
    }
}
