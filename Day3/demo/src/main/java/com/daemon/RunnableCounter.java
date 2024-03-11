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
        System.out.println("되니?");
        thread.interrupt();
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isAlive() {
        return thread.isAlive();
    }


    public static void main(String[] args) {

        RunnableCounter counter1 = new RunnableCounter("counter1", 10);
        counter1.start();

        if (counter1.getCount() > 5) {
            counter1.stop();
        }
    }
}

