package com.thread;

public class SharedCounter2 extends Thread {
    SharedCount sharedCount;
    int count;
    int maxCount;  

    public SharedCounter2(String name, int maxCount, SharedCount sharedCount) {
        setName(name);
        this.sharedCount = sharedCount;
        this.maxCount = maxCount;
        count = 0;
    }

    @Override
    public void run() {
        while (count < maxCount) {
            count++;
            synchronized(sharedCount) {
                sharedCount.increment2();
            }
        }
    }
}
