package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/my-calendar-i/
 *
 * 思路简述：顺序链表
 */
public class MyCalendarI {

    private Interval interval;

    public MyCalendarI() {
        this.interval = new Interval(0, Integer.MAX_VALUE, false);
        this.interval.next = null;
    }

    public boolean book(int start, int end) {
        Interval intervalNode = this.interval;
        while (intervalNode != null) {
            if (intervalNode.start > start) {
                return false;
            }
            if (intervalNode.isInnerInterval(start, end) && !intervalNode.occupied) {
                intervalNode.insert(start, end);
                return true;
            }
            intervalNode = intervalNode.next;
        }
        return false;
    }

    private class Interval {
        private int start;
        private int end;

        /**
         * 该区间是否被预定
         */
        private boolean occupied;

        private Interval next;


        public Interval(int start, int end, boolean occupied) {
            this.start = start;
            this.end = end;
            this.occupied = occupied;
        }

        public boolean isInnerInterval(int start, int end) {
            return start >= this.start && end <= this.end;
        }

        public void insert(int start, int end) {
            if (start == this.start && end == this.end) {
                occupied = true;
            } else if (start != this.start && end == this.end) {
                Interval first = new Interval(start, end, true);
                this.end = start;
                first.next = this.next;
                this.next = first;
            } else if (start == this.start) {
                Interval first = new Interval(end, this.end, false);
                this.end = end;
                this.occupied = true;
                first.next = this.next;
                this.next = first;
            } else {
                Interval first = new Interval(start, end, true);
                Interval second = new Interval(end, this.end, false);
                first.next = second;
                this.end = start;
                second.next = this.next;
                this.next = first;
            }
        }
    }
}
