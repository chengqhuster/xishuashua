package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/three-equal-parts/
 *
 * 思路简述：按照1的数量初步划分区域，结尾0的数量由第三区间结尾0的个数确定
 *          再按照结尾0数量精确划分，最后比较三区间的二进制数是否相同
 *
 */

public class ThreeEqualParts {
    private int[] badCase = new int[]{-1, -1};

    public int[] threeEqualParts(int[] A) {
        int zeroCount = 0;
        int pos = A.length - 1;
        while (pos >= 0 && A[pos] == 0) {
            zeroCount++;
            pos--;
        }
        int oneCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                oneCount++;
            }
        }
        if (oneCount == 0) {
            return new int[]{0,2};
        } else if (oneCount%3 != 0) {
            return badCase;
        } else {
            int index = A.length - 1;
            int firstIndex = 0, secondIndex = 0, thirdIndex = 0;
            int[] res = new int[2];
            int onePart = oneCount/3;
            while (onePart > 0) {
                if (A[index] == 1) {
                    onePart--;
                }
                index--;
            }
            int prevZeroCount = getPrevZeroCount(A, index);
            if (prevZeroCount < zeroCount) {
                return badCase;
            } else {
                thirdIndex = index + 1;
                res[1] = index + 1 - (prevZeroCount - zeroCount);
                onePart = oneCount/3;
                while (onePart > 0) {
                    if (A[index] == 1) {
                        onePart--;
                    }
                    index--;
                }
                prevZeroCount = getPrevZeroCount(A, index);
                if (prevZeroCount < zeroCount) {
                    return badCase;
                } else {
                    secondIndex = index + 1;
                    res[0] = index - (prevZeroCount - zeroCount);
                    while (A[firstIndex] == 0) {
                        firstIndex++;
                    }
                    onePart = oneCount/3;
                    while (onePart > 0) {
                        int sum = A[firstIndex] + A[secondIndex] + A[thirdIndex];
                        firstIndex++;
                        secondIndex++;
                        thirdIndex++;
                        if (sum == 0) {
                            continue;
                        } else if (sum == 3) {
                            onePart--;
                        } else {
                            return badCase;
                        }
                    }
                    return res;
                }
            }
        }
    }

    private int getPrevZeroCount(int[] A, int index) {
        int count = 0;
        while (index >= 0 && A[index] == 0) {
            count++;
            index--;
        }
        return count;
    }
}
