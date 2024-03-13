package com.day3;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private static final int MIN_BUY_SECOND = 1000;
    private static final int MAX_BUY_SECOND = 10000;
    private String name;
    private List<Store> stores;

    public Consumer(String name, List<Store> stores) {
        this.name = name;
        this.stores = stores;
    }

    @Override
    public void run() {
        for (Store store : stores) {
            try {
                store.enter();
                store.buy();
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_BUY_SECOND, MAX_BUY_SECOND + 1));
                store.exit();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}