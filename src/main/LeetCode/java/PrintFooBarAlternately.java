package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/print-foobar-alternately/
 *
 * 思路简述：使用信号量
 *
 */

import java.util.concurrent.Semaphore;

public class PrintFooBarAlternately {
    private int n;
    private Semaphore fo = new Semaphore(1);
    private Semaphore ba = new Semaphore(0);

    public PrintFooBarAlternately(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fo.acquire();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
        	ba.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            ba.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
        	fo.release();
        }
    }
}
