package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/print-in-order/
 *
 * 思路简述：线程控制 也可以使用信号量 Semaphore (最大许可数为 1 的 Semaphore 与 Lock 的区别是，释放一个信号量的许可之前
 * 不需要先获得该信号量的许可，注意如果使用 Condition 的话，可能存在信号丢失问题)
 *
 */

import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrder {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 1;
    private int sleepTime = 20;

    public PrintInOrder() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        boolean complete = false;
        while (!complete) {
            lock.lock();
            if (count == 1) {
                printFirst.run();
                count++;
                complete = true;
            }
            lock.unlock();
            if (!complete) {
                Thread.sleep(sleepTime);
            }
        }
        // printFirst.run() outputs "first". Do not change or remove this line.
    }

    public void second(Runnable printSecond) throws InterruptedException {
        boolean complete = false;
        while (!complete) {
            lock.lock();
            if (count == 2) {
                printSecond.run();
                count++;
                complete = true;
            }
            lock.unlock();
            if (!complete) {
                Thread.sleep(sleepTime);
            }
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
    }

    public void third(Runnable printThird) throws InterruptedException {
        boolean complete = false;
        while (!complete) {
            lock.lock();
            if (count == 3) {
                printThird.run();
                count++;
                complete = true;
            }
            lock.unlock();
            if (!complete) {
                Thread.sleep(sleepTime);
            }
        }
        // printThird.run() outputs "third". Do not change or remove this line.
    }
}
