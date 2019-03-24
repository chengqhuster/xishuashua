package LeetCode.java.DataStruct;

public class UnionAndFind {
    private int[] id;

    public void reset(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    public void union(int x, int y) {
        int[] xId = findWithDepth(x);
        int[] yId = findWithDepth(y);
        if (xId[0] != yId[0]) {
            if (xId[1] > yId[1]) {
                id[yId[0]] = id[xId[0]];
            } else {
                id[xId[0]] = id[yId[0]];
            }
        }
    }

    public int find(int num) {
        while(id[num] != num) {
            // reduce tree deep
            id[num] = id[id[num]];
            num = id[num];
        }
        return num;
    }

    public int[] findWithDepth(int target) {
        int[] res = new int[2];
        int deep = 0;
        while(id[target] != target) {
            // reduce tree deep
            id[target] = id[id[target]];
            target = id[target];
            deep++;
        }
        res[0] = target;
        res[1] = deep;
        return res;
    }
}
