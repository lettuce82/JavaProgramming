package com.ex10;

public class Test {
    public class Main {
        public static void main(String[] args) {
            Store store = new Store();
            Producer producer = new Producer(store);
            Consumer consumer1 = new Consumer("Consumer1", store);
            Consumer consumer2 = new Consumer("Consumer2", store);
    
            Thread producerThread = new Thread(producer);
            Thread consumerThread1 = new Thread(consumer1);
            Thread consumerThread2 = new Thread(consumer2);
    
            producerThread.start();
            consumerThread1.start();
            consumerThread2.start();
        }
    }
    
}
