package com.nio.demo.future;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by roger.lu on 2017/12/29.
 */
public class CompeletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("task started at " + new Date());
            try {
                // time cost
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finished at " + new Date());
            return "task result " + new Date();
        }, executor);
//            future.thenAccept(System.out::println);
        System.out.println("start to thenApply at " + new Date());
        CompletableFuture<Integer> integerCompletableFuture = future.thenApply(s -> s.length());
        System.out.println("end to thenApply at " + new Date());
        System.out.println(integerCompletableFuture.get());

        System.out.println("Main thread finished.");
        executor.shutdown();
    }

}
