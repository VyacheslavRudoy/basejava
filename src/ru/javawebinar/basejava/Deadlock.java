package ru.javawebinar.basejava;

public class Deadlock {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String args[]) {
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        T1.start();
        T2.start();
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            String nameThread = "Thread 1";
            Deadlock.closingThread(Lock1, Lock2, nameThread);
        }
    }

    private static class ThreadDemo2 extends Thread {
        public void run() {
            String nameThread = "Thread 2";
            Deadlock.closingThread(Lock2, Lock1, nameThread);
        }
    }

    public static void closingThread(Object lock1, Object lock2, String nameThread) {
        synchronized (lock1) {
            System.out.println(nameThread + " : Holding " + nameThread + "...");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println(nameThread + " : Waiting for lock others thread...");

            synchronized (lock2) {
                System.out.println(nameThread + " : Holding lock 1 and 2...");
            }
        }
    }
}
