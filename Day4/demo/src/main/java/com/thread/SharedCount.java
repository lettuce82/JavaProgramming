package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedCount {
    //Lock lock = new ReentrantLock();
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        //lock.lock();
        setCount(getCount() + 1);
        //lock.unlock();

    }
    public void increment2() {
        //lock.lock();
        synchronized(this) {
            setCount(getCount() + 1);
        }
        //lock.unlock();
    }
}
