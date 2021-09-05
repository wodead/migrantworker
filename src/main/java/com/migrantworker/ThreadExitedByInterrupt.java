package com.migrantworker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread1 会不会正常退出？
 */
public class ThreadExitedByInterrupt {

    public static void main(String[] args) {
        Thread thread1 = new Thread(()-> {
            for(int i = 0; i < 2000000000; i++) {
                System.out.println(i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Job is terminated!");
                    break;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Interrupted 状态会被清除。
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        new Thread(()-> {
            try {
                //3秒后中断thread1
                Thread.sleep(3000);
                thread1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
