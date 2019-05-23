package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/set-matrix-zeroes/
 *
 * 思路简述：重点在于直线的表示上 a * x - b * y = c
 *         引入 hashMap 维持 Point 到 Line 的映射, 解决重复点的问题
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MaxPointsOnALine {
    HashMap<Point, List<Line>> map = new HashMap<>();
    ArrayList<Line> lines = new ArrayList<>();
    int res = 0;

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return res;
        }
        if (points.length <= 2) {
            return points.length;
        }
        res = 2;
        HashSet<Point> set = new HashSet<>();
        set.add(new Point(points[0][0], points[0][1]));
        ArrayList<Point> unDeal = new ArrayList<>();
        for (int i = 1; i < points.length; i++) {
            Point p = new Point(points[i][0], points[i][1]);
            if (set.contains(p)) {
                unDeal.add(p);
                continue;
            }
            set.add(p);
            if (!isNodeInSomeLine(p)) {
                for (int j = 0; j < i; j++) {
                    Point q = new Point(points[j][0], points[j][1]);
                    Line line = generateLine(points[j], points[i]);
                    lines.add(line);
                    if (!map.containsKey(p)) {
                        map.put(p, new ArrayList<>());
                    }
                    map.get(p).add(line);
                    if (!map.containsKey(q)) {
                        map.put(q, new ArrayList<>());
                    }
                    map.get(q).add(line);
                }
            }
        }
        for (Point p : unDeal) {
            if (map.containsKey(p)) {
                for (Line line : map.get(p)) {
                    int count = line.addPoint();
                    res = Math.max(res, count);
                }
            }
        }
        // all points coincide
        if (set.size() == 1) {
            return points.length;
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
            a = pointA[1] - pointB[1];
            b = pointA[0] - pointB[0];
            c = a * pointA[0] - b * pointA[1];
        }
        return new Line(a, b, c);
    }

    private boolean isNodeInSomeLine(Point p) {
        for (Line line : lines) {
            if (line.isOnline(p)) {
                if (!map.containsKey(p)) {
                    map.put(p, new ArrayList<>());
                }
                map.get(p).add(line);
                int count = line.addPoint();
                res = Math.max(res, count);
                return true;
            }
        }
        return false;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Point) {
                Point cmp = (Point) obj;
                return cmp.x == x && cmp.y == y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            String hashStr = "" + x + y;
            return hashStr.hashCode();
        }
    }

    class Line {
        long a, b, c;
        int points;

        Line(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
            points = 2;
        }

        int addPoint() {
            return ++points;
        }

        boolean isOnLine(int x, int y) {
            return a * x - b * y == c;
        }

        boolean isOnline(Point p) {
            return isOnLine(p.x, p.y);
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
