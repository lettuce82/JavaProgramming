package com.thread;

import java.time.LocalDateTime;

public class Counter {
    int currentCount = 0;
    int maxCount = 0;
    String name;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }
    
    public void run() {
        try {
            while (currentCount < maxCount) {
                Thread.sleep(1000);
                currentCount++;
                System.out.println(this.name + " : " + this.currentCount);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
        Counter counter1 = new Counter("counter1", 10);
        Counter counter2 = new Counter("counter2", 10);
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println("start: " + localDate.now());
        counter1.run();
        counter2.run();
        System.out.println("end: " + localDate.now());
    }
}
