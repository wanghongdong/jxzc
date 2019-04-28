package com.jxzc.web.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorCase {

    private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        List list = new ArrayList();
        while (true) {
            list.add(123);
        }
    }


    public static void demo(){
        for (int i = 0; i < 100; i++) {
            executor.execute(new Task(i));
        }
    }

    static class Task implements Runnable{

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "："+i);
        }
    }

}

