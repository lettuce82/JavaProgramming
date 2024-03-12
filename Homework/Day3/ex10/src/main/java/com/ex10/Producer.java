package com.ex10;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private static final int MIN_PRODUCE_SECOND = 1000;
    private static final int MAX_PRODUCE_SECOND = 10000;
    private Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_PRODUCE_SECOND, MAX_PRODUCE_SECOND + 1));
            store.sell();
            System.out.println("물품을 채웠습니다.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}