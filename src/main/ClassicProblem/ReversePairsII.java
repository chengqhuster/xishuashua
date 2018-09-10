package ClassicProblem;

/*
 * 题目描述：http://www.cppblog.com/menjitianya/archive/2015/11/02/212171.html 例题四
 *          给定N(N <= 100000)个区间，定义两个区间(Si, Ei)和(Sj, Ej)的'>'如下：如果Si <= Sj and Ej <= Ei
 *          and Ei - Si > Ej - Sj,则 (Si, Ei) > (Sj, Ej)，现在要求区间相互包含(满足上述关系)的最大层数
 *
 * 思路简述：采用BIT数据结构，对区间进行排序，排序规则为：左端点递增，如果左端点相同，则右端点递减。然后枚举
 *          区间，不断插入区间右端点，因为区间左端点是保持递增的，所以对于某个区间(Si, Ei)，只需要查询树状数组
 *          中[Ei, MAX]这一段有多少已经插入的数据，就能知道有多少个区间是比它大的，这里需要注意的是多个区间相等
 *          的情况，因为有排序，所以它们在排序后的数组中一定是相邻的，所以在遇到有相等区间的情况，需要"延迟"插入。
 *          等下一个不相等区间出现时才把之前保存下来的区间右端点进行插入。插入完毕再进行统计。
 *
 *     分析：与ReversePairs其实是相同的类型，ReservePairs了以看作(1,5) (2,4) ...... 的形式(前者为数组下标)
 *
 */

import DataStruct.BIT;

import java.util.ArrayDeque;
import java.util.Arrays;

public class ReversePairsII {
    public int getReversePairs(int[][] a) {
        if(a == null || a.length == 0 || a[0].length !=2)
            return 0;
        Arrays.sort(a, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            max = max < a[i][1] ? a[i][1] : max;
            min = min > a[i][1] ? a[i][1] : min;
        }
        BIT bit = new BIT(max - min + 1);
        int[] res = new int[a.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i=0; i<a.length; i++) {
            if(i > 0 && a[i][0] == a[i-1][0] && a[i][1] == a[i-1][1]) {
                res[i] = res[i-1];
                stack.push(a[i][0]);
            }
            else {
                if(!stack.isEmpty()) {
                    int num = stack.pop();
                    bit.add(num - min + 1, 1);
                }
                // 计算[a[i][1] - min +  1, MAX]的区间和(左右均为闭区间)
                res[i] = bit.sum(max - min + 1) - bit.sum(a[i][1] - min);
                bit.add(a[i][1] - min + 1, 1);
            }
        }
        // 最小层数为 1
        return Arrays.stream(res).max().getAsInt() + 1;
    }
}
