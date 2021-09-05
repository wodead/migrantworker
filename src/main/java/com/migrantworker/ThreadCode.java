package com.migrantworker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCode {

    private int i = 0;

    private ReentrantLock lock = new ReentrantLock();       //定义锁对象

    private Condition condition = lock.newCondition();

    public void increase()  {
        lock.lock();
        try {
            if (i > 0) condition.await();
            i++;
            System.out.println(i);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease()  {
        lock.lock();
        try {
            if (i <= 0) condition.await();
            i--;
            System.out.println(i);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadCode threadCode = new ThreadCode();

            new Thread() {
                public void run() {
                    for (int i = 0; i < 400; i++) {
                        threadCode.increase();
                    }
                }
            }.start();



            new Thread() {
                public void run() {
                    for (int i = 0; i < 400; i++) {
                        threadCode.decrease();
                    }
                }
            }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.increase();
                }
            }
        }.start();



        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.decrease();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.increase();
                }
            }
        }.start();



        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.decrease();
                }
            }
        }.start();
        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.increase();
                }
            }
        }.start();



        new Thread() {
            public void run() {
                for (int i = 0; i < 400; i++) {
                    threadCode.decrease();
                }
            }
        }.start();
    }
}
