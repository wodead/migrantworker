package com.migrantworker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lz
 * @date: 2021/6/30
 */
public class Code2 {

    static int iStop = 150;
    static int i = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static class Thread1 extends Thread {
        public void run() {
            try {
                lock.lock();
                while(i < iStop) {
                    if (i % 3 == 0) {
                        int value = 2 * (i / 3 + 1);
                        if (value < 50) {
                            System.out.println(value);
                        } else {
                            System.out.println("complete!");
                        }
                        i++;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    static class Thread2 extends Thread {

        public void run() {
            try {
                lock.lock();
                while(i < iStop) {
                    if (i % 3 == 1) {
                        int value = 3 * (i / 3 + 1);
                        if (value < 50) {
                            System.out.println(value);
                        } else {
                            System.out.println("complete!");
                        }
                        i++;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    static class Thread3 extends Thread {
        public void run() {

            try {
                lock.lock();
                while(i < iStop) {
                    if (i % 3 == 2) {
                        int value = 5 * (i / 3 + 1);
                        if (value < 50) {
                            System.out.println(value);
                        } else {
                            System.out.println("complete!");
                        }
                        i++;
                        condition.signalAll();
                    } else {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String args[]) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
