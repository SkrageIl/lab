package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread;
        ArrayList<ThreadsCalc> threadsCalcArrayList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ThreadsCalc threadsCalc = new ThreadsCalc(i);
            thread = new Thread(threadsCalc);
            thread.start();
            threadsCalcArrayList.add(threadsCalc);
            while (!threadsCalc.isEnd()) {
                Thread.sleep(5);
            }
        }
        threadsCalcArrayList.stream().sorted(Comparator.comparing(ThreadsCalc::getWhatTimeIsIt)).forEach(System.out::println);
    }
}
