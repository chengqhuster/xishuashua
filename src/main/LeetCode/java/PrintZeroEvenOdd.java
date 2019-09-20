package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/print-zero-even-odd/
 *
 * 思路简述：信号量
 *
 */

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
    private int n;
    private Semaphore ze = new Semaphore(1);
    private Semaphore ev = new Semaphore(0);
    private Semaphore od = new Semaphore(0);

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            ze.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                ev.release();
            } else {
                od.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            ev.acquire();
            printNumber.accept(i);
            ze.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            od.acquire();
            printNumber.accept(i);
            ze.release();
        }
    }
}
