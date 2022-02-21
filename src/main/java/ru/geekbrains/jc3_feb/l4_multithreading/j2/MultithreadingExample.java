package ru.geekbrains.jc3_feb.l4_multithreading.j2;

public class MultithreadingExample {

    private static final Object mon1 = new Object();
    private static final Object mon2 = new Object();

    private static int a = 0;
    private static int b = 0;
    private static int c = 0;


    public static void main(String[] args) {
//        threadCreation();
//        threadStopExample();
//        raceConditionExample();


    }



    private static void raceConditionExample() {
        Thread t1 = new Thread(MultithreadingExample::increment);
        Thread t2 = new Thread(MultithreadingExample::increment);
        Thread t3 = new Thread(MultithreadingExample::increment);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("A = %d, B = %d, C = %d\n", a, b, c);
    }

    private static void unSyncMethod() {
        //.... some logic to be sync
    }


    private static void monitorSync() {
        System.out.println("do smth sync....");
        //......

        synchronized (mon1) {
            //.... here is sync code
        }


    }

    private static void monitorSync2() {
        System.out.println("do smth sync2....");
        //......

        synchronized (mon2) {
            //.... here is sync code
        }


    }

    private static synchronized void increment() { //mon is class
        for (int i = 0; i < 1000; i++) {
            a++;
            b++;
            c++;
        }
    }

    private synchronized void doSync() { //mon is this
        //....
    }



    private static void threadStopExample() {
        Thread main = Thread.currentThread();

        Thread t = new Thread(() -> {
//            while (true) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("ffffff - " + main.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
//        t.setDaemon(true);
        t.start();

        try {
            Thread.sleep(3000);
            System.out.println("Main finish");
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        t.stop();
//        t.suspend();
//        t.resume();
    }

    private static void threadCreation() {
        MyThread myThread = new MyThread();
//        myThread.run();
        myThread.start();
        MyRunnable myRunnable = new MyRunnable();
        Thread myRunnableThread = new Thread(myRunnable);
        myRunnableThread.start();
        Thread anonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("Hello from anonymous. Thread is %s\n", Thread.currentThread().getName());
            }
        });
        anonThread.start();
        Thread lambdaThread = new Thread(() -> System.out.printf("Hello from lambda. Thread is %s\n", Thread.currentThread().getName()));
        lambdaThread.start();
        new Thread(() -> System.out.printf("Hello from lambda one line. Thread is %s\n", Thread.currentThread().getName())).start();
        new Thread(() -> System.out.printf("Hello from lambda one line 2. Thread is %s\n", Thread.currentThread().getName())).start();


        System.out.printf("Hello from main. Thread is %s\n", Thread.currentThread().getName());


        new Thread(() -> printSome()).start();
        new Thread(MultithreadingExample::printSome).start();
        new Thread(MultithreadingExample::printSome).start();
        new Thread(MultithreadingExample::printSome).start();
        new Thread(MultithreadingExample::printSome).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRunnableThread.start();
    }

    private static void printSome() {
        System.out.println("Print some " + Thread.currentThread().getName());
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.printf("Hello from my runnable. Thread is %s\n", Thread.currentThread().getName());
        }
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.printf("Hello from my thread. Thread is %s\n", Thread.currentThread().getName());
        }


    }
}
