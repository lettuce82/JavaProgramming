package com.day3;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private static final int MIN_PRODUCE_SECOND = 1000;
    private static final int MAX_PRODUCE_SECOND = 10000;
    private String name;
    private List<Store> stores;

    public Producer(String name, List<Store> stores) {
        this.name = name;
        this.stores = stores;
    }

    @Override
    public void run() {
        for (Store store : stores) {
            try {
                store.sell();
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_PRODUCE_SECOND, MAX_PRODUCE_SECOND + 1));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}