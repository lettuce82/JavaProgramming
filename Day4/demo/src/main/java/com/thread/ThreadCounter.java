package com.thread;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ThreadCounter extends Thread {
    int currentCount = 0;
    int maxCount = 0;
    String name;

    public ThreadCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }
    
    @Override
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
        LocalDateTime localDate = LocalDateTime.now();
        ThreadCounter counter1 = new ThreadCounter("counter1", 10);
        ThreadCounter counter2 = new ThreadCounter("counter2", 10);
        System.out.println("start: " + localDate.now());
        counter1.start();
        counter2.start();
        System.out.println("end: " + localDate.now());
    }
}
