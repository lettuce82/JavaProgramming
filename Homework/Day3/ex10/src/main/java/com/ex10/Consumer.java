package com.ex10;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private static final int MIN_BUY_SECOND = 1000;
    private static final int MAX_BUY_SECOND = 10000;
    private String name;
    private Store store;

    public Consumer(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        try {
            store.enter();
            System.out.println(name + "이(가) 가게에 들어갔습니다.");
            store.buy();
            System.out.println(name + "이(가) 물건을 구매했습니다.");
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_BUY_SECOND, MAX_BUY_SECOND + 1));
            store.exit();
            System.out.println(name + "이(가) 가게를 나갔습니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}