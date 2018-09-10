package DataStruct;

/*
 * 树状数组(二叉索引树)：https://leetcode.com/problems/frog-jump/description/
 *
 *   数组A[N]代表输入的元素组成的数组
 *   C[i] = sum{ A[j] |  i - 2^k + 1 <= j <= i } (k来表示i的二进制末尾0的个数)
 *   sum(i) = sum{ A[j] | 1 <=j <=i }
 *          = A[1] + A[2] + ... + A[i]
 *          = A[1] + A[2] + A[i-2^k] + A[i-2^k+1] + ... + A[i]
 *          = A[1] + A[2] + A[i-2^k] + C[i]
 *          = sum(i - 2^k) + C[i]
 *          = sum( i - lowbit(i) ) + C[i]
 *
 *   PUIQ模型(Point Update Interval Query 点更新，段求和)
 *   IUPQ模型(Interval Update Point Query 段更新，点求值)
 *
 */

public class BIT {
    private int N;
    private int[] C;

    public BIT(int N) {
        this.N = N;
        C = new int[N+1];
    }

    // 取x的最右边出现的1的值
    private int lowbit(int x) {
        return x & -x;
    }

    // 第x位元素增加v(递归形式)
//    public void add(int x, int v) {
//        if(x <= N) {
//            C[x] += v;
//            add(x + lowbit(x), v);
//        }
//    }

    // 第x位元素增加v(迭代形式)
    public void add(int x, int v) {
        while(x <= N) {
            C[x] += v;
            x += lowbit(x);
        }
    }

    // 求sum(递归形式)
//    public int sum(int x) {
//        return x > 0 ? C[x] + sum(x - lowbit(x)) : 0;
//    }

    // 求sum(迭代形式)
    public int sum(int x) {
        int s = 0;
        while(x > 0) {
            s += C[x];
            x = x - lowbit(x);
        }
        return s;
    }
}
