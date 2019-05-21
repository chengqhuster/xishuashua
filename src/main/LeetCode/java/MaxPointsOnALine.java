package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/set-matrix-zeroes/
 *
 * 思路简述：重点在于直线的表示上 a * x - b * y = c (没有解决重复节点的问题)
 *
 */

import java.util.HashMap;

public class MaxPointsOnALine {
    HashMap<Line, Integer> map = new HashMap<>();
    int res = 0;

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return res;
        }
        if (points.length <= 2) {
            return points.length;
        }
        res = 2;
        for (int i = 1; i < points.length; i++) {
            if (!isNodeInSomeLine(points[i][0], points[i][1])) {
                for (int j = 0; j < i; j++) {
                    Line line = generateLine(points[j], points[i]);
                    map.put(line, 2);
                }
            }
        }
        return res;
    }

    private Line generateLine(int[] pointA, int[] pointB) {
        int a, b, c;
        if (pointA[0] == pointB[0]) {
            a = 1;
            b = 0;
            c = pointA[0];
        } else if (pointA[1] == pointB[1]) {
            a = 0;
            b = -1;
            c = pointA[1];
        } else {
            int width = pointA[0] - pointB[0];
            int height = pointA[1] - pointB[1];
            int maxApproximation = getMaxApproximation(Math.max(Math.abs(width), Math.abs(height)),
                    Math.min(Math.abs(width), Math.abs(height)));
            width = width / maxApproximation;
            height = height / maxApproximation;
            a = height;
            b = width;
            c = a * pointA[0] - b * pointA[1];
        }
        return new Line(a, b, c);
    }

    // 最大公约数
    private int getMaxApproximation(int a, int b) {
        while (true) {
            int mod = a % b;
            if (mod == 0) {
                break;
            } else {
                a = b;
                b = mod;
            }
        }
        return b;
    }

    private boolean isNodeInSomeLine(int x, int y) {
        if (map.isEmpty()) {
            return false;
        }
        for (Line key : map.keySet()) {
            if (key.isOnLine(x, y)) {
                int count = map.get(key) + 1;
                map.put(key, count);
                res = Math.max(res, count);
                return true;
            }
        }
        return false;
    }

    class Line {
        long a, b, c;

        Line(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        boolean isOnLine(int x, int y) {
            return a * x - b * y == c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Line) {
                Line cmp = (Line)obj;
                return cmp.a == a && cmp.b == b && cmp.c == c;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            String hashStr = "" + a + b + c;
            return hashStr.hashCode();
        }
    }
}
