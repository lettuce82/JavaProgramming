package com.ex10;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            consumers.add(new Consumer("consumer" + (i + 1), store));
        }
        
        List<Thread> conThreads = new ArrayList<>();
        Thread producerThread = new Thread(producer);
        for (Consumer consumer : consumers) {
            conThreads.add(new Thread(consumer));
        }

        
        for (Thread thread : conThreads) {
            thread.start();
        }
        producerThread.start();
    }
}
