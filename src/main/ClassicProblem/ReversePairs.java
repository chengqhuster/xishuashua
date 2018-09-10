package ClassicProblem;

/*
 * 题目描述：http://www.cppblog.com/menjitianya/archive/2015/11/02/212171.html 例题三
 *
 * 思路简述：采用BIT数据结构，输入数组元素作为下标，从后往前遍历执行add(a[i])，此时的sun(a[i])
 *          即为a[i]为首的逆序数量
 *
 *     不足：元素不能为负数(整体偏移)，元素可能分布很分散(树状数组很大，空间浪费)
 *
 */

import DataStruct.BIT;

public class ReversePairs {
    public int getReversePairs(int[] a) {
        if(a == null || a.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            max = max < a[i] ? a[i] : max;
            min = min > a[i] ? a[i] : min;
        }
        int res = 0;
        BIT bit = new BIT(max - min + 1);
        for(int i=a.length-1; i>=0; i--) {
            res += bit.sum(a[i] - min + 1);
            bit.add(a[i]-min+1, 1);
        }
        return res;
    }
}
