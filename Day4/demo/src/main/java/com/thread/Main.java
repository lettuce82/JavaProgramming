package com.thread;

public class Main {
    public static void main(String[] args) {
        Object lock = new Object();
        // Data data = new Data();
        // Thread sender = new Thread(new Sender(data));
        // Thread sender2 = new Thread(new Sender(data));
        // Thread receiver = new Thread(new Receiver(data));
        
        // sender.start();
        // sender2.start();
        // receiver.start();
        


        Thread thread = new Thread(() -> {
            // long count = 0;
            // while (count < 100000000) {
            //     count++;
            //}

            // for (int i = 0; i < 10; i++) {
            //     try {
            //         Thread.sleep(1000);
            //     } catch (InterruptedException e) {
            //         Thread.currentThread().interrupt();
            //     }
            // }
            synchronized(lock) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "thread2");


        System.out.println(thread.getState());
        System.out.println(thread2.getState());

        int count = 0;
        while (thread.isAlive() || thread2.isAlive()) {
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getState() + ", " + thread2.getState());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
                count++;
                if (count == 5) {
                    
                }
            }
        }


        // Printer printer1 = new Printer("print1");
        // Printer printer2 = new Printer("print2");

        // printer1.start();
        // printer2.start();

        // while (printer1.isAlive() || printer2.isAlive()) {
        //     System.out.println("print1: " + printer1.getState());
        //     System.out.println("print2: " + printer2.getState());
        // }
    }
}

// class Printer extends Thread {
//     int num = 0;
//     String name;
//     public Printer(String name) {
//         this.name = name;
//     }

//     @Override
//     public void run() {
//         synchronized(this) {
//             while (num < 5) {
//                 System.out.println(name + ": " + num);
//                 num++;
//             }
//         }
        
//     }
// }