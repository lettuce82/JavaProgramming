package main.java.com.daemon;

import java.time.LocalDateTime;

public class RunnableCounter implements Runnable {
    int currentCount = 0;
    int maxCount = 0;
    String name;
    Thread thread;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        thread = new Thread(this);
    }

    public int getCount() {
        return this.currentCount;
    }
    
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && currentCount < maxCount) {
                Thread.sleep(1000);
                currentCount++;
                System.out.println(this.name + " : " + this.currentCount);
                // if (getCount() > 5) {
                //     stop();
                // }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void start() {
        thread.start();
    }
    
    public void stop() {
        thread.interrupt();
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isAlive() {
        return thread.isAlive();
    }


    public static void main(String[] args) {

        RunnableCounter[] counters = new RunnableCounter[10];
        

        for (int i = 0; i < 10; i++) {
            counters[i] = new RunnableCounter("counter" + (i + 1), 10);

            counters[i].getThread().start();
        }

        boolean allStopped = false;
        while (!allStopped) {
            if (counters[0].getCount() > 5) {
                for (int i = 0; i < counters.length; i++) {
                    counters[i].stop();
                }
            }

            allStopped = true;
            for (int i = 0; i < counters.length; i++) {
                if (counters[i].isAlive() ) {
                    allStopped = false;
                }
            }
        }
    }
}