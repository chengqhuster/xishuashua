package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/similar-string-groups/description/
 *
 * 思路简述：并查集
 *
 */


public class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        if(A == null || A.length == 0) {
            return 0;
        }
        int N = A.length;
        int[] id = new int[N];
        int res = N;
        for(int i=0; i<N; i++) {
            id[i] = i;
        }
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                if(isSimilarStrings(A[i], A[j])) {
                    res = judgeAndUnion(id, i, j, res);
                }
            }
        }
        return res;
    }

    private boolean isSimilarStrings(String a, String b) {
        if(a == null || b == null)
            return false;
        if(a.length() != b.length())
            return false;
        int diff = 0;
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                diff = diff + 1;
                if(diff > 2)
                    return false;
            }
        }
        return (diff == 2 || diff == 0);
    }

    private int judgeAndUnion(int[] id, int a, int b, int groups) {
        int[] aID = find(id ,a);
        int[] bID = find(id ,b);
        if(aID[0] != bID[0]) {
            // balance tree
            if(aID[1] > bID[1]) {
                id[bID[0]] = aID[0];
            } else {
                id[aID[0]] = bID[0];
            }
            return groups - 1;
        }
        return groups;
    }

    private int[] find(int[] id, int num) {
        int[] res = new int[2];
        int deep = 0;
        while(id[num] != num) {
            // reduce tree deep
            id[num] = id[id[num]];
            num = id[num];
            deep = deep + 1;
        }
        res[0] = num;
        res[1] = deep;
        return res;
    }
}
