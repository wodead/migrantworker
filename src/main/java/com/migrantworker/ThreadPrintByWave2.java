package com.migrantworker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized 版本3个线程依次输出数字。
 * A  B  C
 * 0
 *    1
 *       2
 *    3
 * 4
 *    5
 *       6
 *    7
 * 8
 *
 * 仔细观察 A  B  C 数字的规律
 * A % 4 = 0
 * B % 2 = 1
 * C % 4 = 2
 *
 */
public class ThreadPrintByWave2 {

    private static int seq = 0;

    private static Object locker = new Object();

    public static void print(int n) {
        new Thread(()-> {
            synchronized (locker) {
                while (seq < n) {
                    if (seq % 4 == 0) {
                        System.out.println("A: " + seq);
                        seq++;
                        locker.notifyAll();
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(()-> {
            synchronized (locker) {
                while (seq < n) {
                    if (seq % 2 == 1) {
                        System.out.println("B: " + seq);
                        seq++;
                        locker.notifyAll();
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(()-> {
            synchronized (locker) {
                while (seq < n) {
                    if (seq % 4 == 2) {
                        System.out.println("C: " + seq);
                        seq++;
                        locker.notifyAll();
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        print(10000);
    }
}
