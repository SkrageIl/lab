package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsCalc implements Runnable {
    int threads;

    private AtomicInteger count = new AtomicInteger(0);

    public boolean isEnd() {
        return  threads == count.get();
    }

    private Double sum = 0d;

    private long whatTimeIsIt;

    public long getWhatTimeIsIt() {
        return whatTimeIsIt;
    }

    @Override
    public String toString() {
        return  "threads=" + threads +
                ", sum=" + sum +
                ", Time=" + whatTimeIsIt;
    }

    ThreadsCalc(int threads) {
        this.threads = threads;
    }

    private synchronized void addSum(double value) {
        sum += value;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        double a = 0;
        double b = Math.PI;
        double interval = b - a;
        double step = interval / threads;
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            new Thread(()->{
                addSum(new Integral(x -> Math.sin(x), (a + finalI * step), (a + (finalI + 1) * step), Math.pow(10, -8)).calc());
                count.incrementAndGet();
            }).start();
        }
        while (!isEnd()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        whatTimeIsIt = System.currentTimeMillis() - time;
       System.out.println("T: " + threads + " ; x = " + sum + " ; time = " + whatTimeIsIt);
    }
}
