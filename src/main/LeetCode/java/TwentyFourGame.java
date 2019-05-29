package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/24-game/
 *
 * 思路简述：用分数表示运算过程，dfs
 *
 */

import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame {
    private final int TARGET = 24;
    boolean res;

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length != 4) {
            return false;
        }
        res = false;
        List<Fraction> fractions = new ArrayList<>();
        for (int num : nums) {
            fractions.add(new Fraction(num, 1));
        }
        dfs(fractions);
        return res;
    }

    private void dfs(List<Fraction> fractions) {
        if (fractions.size() == 1) {
            Fraction f = fractions.get(0);
            if (f.q != 0 && f.p % f.q == 0 && f.p/f.q == TARGET) {
                res = true;
            }
        } else {
            for (int i = 0; i < fractions.size(); i++) {
                for (int j = i + 1; j < fractions.size(); j++) {
                    List<Fraction> temp = new ArrayList<>(fractions);
                    Fraction a = temp.remove(j);
                    Fraction b = temp.remove(i);
                    Fraction c;
                    // add
                    c = Fraction.add(a, b);
                    temp.add(c);
                    dfs(temp);
                    temp.remove(temp.size() - 1);
                    // plus
                    c = Fraction.plus(a, b);
                    temp.add(c);
                    dfs(temp);
                    temp.remove(temp.size() - 1);
                    // sub a - b
                    c = Fraction.sub(a, b);
                    temp.add(c);
                    dfs(temp);
                    temp.remove(temp.size() - 1);
                    // sub b - a
                    c = Fraction.sub(b, a);
                    temp.add(c);
                    dfs(temp);
                    temp.remove(temp.size() - 1);
                    // minus a / b
                    if (b.q != 0) {
                        c = Fraction.minus(a, b);
                        temp.add(c);
                        dfs(temp);
                        temp.remove(temp.size() - 1);
                    }
                    // minus b / a
                    if (a.q != 0) {
                        c = Fraction.minus(b, a);
                        temp.add(c);
                        dfs(temp);
                    }
                }
            }
        }
    }

    static class Fraction {
        int p, q;

        Fraction(int p, int q) {
            this.p = p;
            this.q = q;
        }

        static Fraction add(Fraction a, Fraction b) {
            int p = a.p * b.q + b.p * a.q;
            int q = a.q * b.q;
            return new Fraction(p, q);
        }

        static Fraction sub(Fraction a, Fraction b) {
            int p = a.p * b.q - b.p * a.q;
            int q = a.q * b.q;
            return new Fraction(p, q);
        }

        static Fraction plus(Fraction a, Fraction b) {
            int p = a.p * b.p;
            int q = a.q * b.q;
            return new Fraction(p, q);
        }

        static Fraction minus(Fraction a, Fraction b) {
            int p = a.p * b.q;
            int q = a.q * b.p;
            return new Fraction(p, q);
        }
    }
}
