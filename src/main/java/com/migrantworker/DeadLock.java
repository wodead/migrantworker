package com.migrantworker;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁
 */
public class DeadLock {
    static ReentrantLock lockA = new ReentrantLock();
    static ReentrantLock lockB = new ReentrantLock();
    static class ThreadA extends Thread {
        int count = 0;
        @Override
        public void run() {
           while(count < 100) {
               try {
                   lockA.lock();
                   System.out.println("Thread1 locked A! " + count);
                   Thread.sleep(100);
                   lockB.lock();
                   System.out.println("Thread1 locked B! " + count);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   lockB.unlock();
                   lockA.unlock();
               }
           }
        }
    }

    static class ThreadB extends Thread {
        int count = 0;
        @Override
        public void run() {
            while(count < 100) {
                try {
                    lockB.lock();
                    System.out.println("Thread2 locked B! " + count);
                    Thread.sleep(100);
                    lockA.lock();
                    System.out.println("Thread2 locked A! " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lockB.unlock();
                    lockA.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }
}
