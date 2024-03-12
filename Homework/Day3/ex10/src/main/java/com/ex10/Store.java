package com.ex10;

public class Store {
    private static final int MAX_PRODUCT = 10;
    private static final int MAX_CUSTOMER = 5;
    int product = 0;
    int customer = 0;

    public Store() {
        this.product = 0;
        this.customer = 0;
    }

    public synchronized void enter() {
        while (customer >= MAX_CUSTOMER) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        customer++;
        notifyAll();
    }

    public synchronized void exit() {
        customer--;
        notifyAll();
    }

    public synchronized void buy() {
        while (product == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        product--;
        System.out.println("물건 팔림: 생산자에게 알림!");
        notifyAll();
    }

    public synchronized void sell() {
        while (product >= MAX_PRODUCT) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        product++;
        System.out.println("물건 들어옴: 고객에게 알림!");
        notifyAll();
    }
}
