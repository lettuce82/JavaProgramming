package main.java.com.daemon;

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
        Counter counter = new Counter("counter", 10);
        counter.run();
    }
}
