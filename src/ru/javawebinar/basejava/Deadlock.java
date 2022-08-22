package ru.javawebinar.basejava;

public class Deadlock {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String args[]) {
        String nameThread1 = "Thread 1";
        String nameThread2 = "Thread 2";
        Deadlock.closingThread(Lock1, Lock2, nameThread1);
        Deadlock.closingThread(Lock2, Lock1, nameThread2);
    }

    public static void closingThread(Object lock1, Object lock2, String nameThread) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println(nameThread + " : Holding " + nameThread + "...");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(nameThread + " : Waiting for lock others thread...");

                    synchronized (lock2) {
                        System.out.println(nameThread + " : Holding lock 1 and 2...");
                    }
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }
}
