package com.migrantworker;

/**
 * A/B/C 三个线程依次打印数字
 * A  B  C
 * 0
 *    1
 *       2
 * 3
 *    4
 *       5
 * 6
 *    7
 *       8
 */
public class ThreadPrintByOrder extends Thread {

    private static int seq = 0;

    private static Object locker = new Object();

    public static void print(int n) {
        new Thread(()-> {
            synchronized (locker) {
                while (seq < n) {
                    if (seq % 3 == 0) {
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
                    if (seq % 3 == 1) {
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
                    if (seq % 3 == 2) {
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
