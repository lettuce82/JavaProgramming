package com.day3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Store {
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    int timeOut = 3000;
    private static final int MAX_PRODUCT = 10;
    private static final int MAX_CUSTOMER = 5;
    static final int WAIT_TIME = 5;
    int maxStoreItemNum = 0;
    int productCount = 0;
    int customerCount = 0;
    String name;

    Semaphore enterSemaphore;
    Semaphore productSemaphore;
    Semaphore sellSemaphore;

    Random random = new Random();

    public Store(String name) {
        this.name = name;
        this.productCount = 0;
        this.customerCount = 0;
        this.maxStoreItemNum = random.nextInt(MAX_PRODUCT) + 1;
        enterSemaphore = new Semaphore(MAX_CUSTOMER);
        productSemaphore = new Semaphore(MAX_PRODUCT);
    }

    public void enter() throws InterruptedException {
        enterSemaphore.acquire();
        customerCount++;
        //logger.info("({})가 ({})에 입장하였습니다. (인원수 : {}/{}명)", Thread.currentThread().getName(), name, customerCount, MAX_CUSTOMER);
        System.out.println(Thread.currentThread().getName() + " 가 " + name + "에 입장하였습니다. (인원수 : " + customerCount + " / " + MAX_CUSTOMER + " )");
    }

    public void exit() {
        enterSemaphore.release();
        customerCount--;
        //logger.info("({}) 가 ({})에서 퇴장하였습니다. (인원수 : {}/{}명)", Thread.currentThread().getName(), name, customerCount, MAX_CUSTOMER);
        System.out.println(Thread.currentThread().getName() + " 가 " + name + "에 퇴장하였습니다. (인원수 : " + customerCount + " / " + MAX_CUSTOMER + " )");
    }

    public synchronized void buy() throws InterruptedException {
        productSemaphore.acquire();
        
        if (productCount <= 0 || (!productSemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            //logger.log(Level.WARN, "({})가 ({})에서 구매 포기 했습니다.(시간 초과)", Thread.currentThread().getName(), name);
            System.out.println(Thread.currentThread().getName() + "가 " + name + "에서 구매 포기 했습니다. (시간초과)");
            return;
        }
        productCount--;
        // logger.info("({})가 ({})에서 구매 했습니다. (재고량 : {}/{})",
        // Thread.currentThread().getName(), name, productCount, MAX_PRODUCT);
        System.out.println(Thread.currentThread().getName() + "가 " + name + "에서 구매했습니다. (재고량 : " + productCount + " / " + maxStoreItemNum + ")");
        productSemaphore.release();
    }

    public synchronized void sell() throws InterruptedException {
        if (productSemaphore.tryAcquire(timeOut, TimeUnit.MILLISECONDS)) {
            
        }
        productSemaphore.acquire();
        if (productCount >= maxStoreItemNum || (!productSemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            //logger.log(Level.WARN, "({})가 ({})에서 납품 포기 했습니다.(시간 초과)", Thread.currentThread().getName(), name);
            System.out.println(Thread.currentThread().getName() + "가 " + name + "에서 납품 포기 했습니다. (시간초과)");
            return;
        }

        productCount++;
        // logger.info("({})가 ({})에 납품 했습니다. (재고량 : {}/{})", 
        // Thread.currentThread().getName(), name, productCount, MAX_PRODUCT);
        System.out.println(Thread.currentThread().getName() + "가 " + name + "에서 납품했습니다. (재고량 : " + productCount + " / " + maxStoreItemNum + ")");
        productSemaphore.release();  
    }
}
