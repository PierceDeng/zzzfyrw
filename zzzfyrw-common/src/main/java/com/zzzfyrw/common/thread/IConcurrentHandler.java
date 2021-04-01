package com.zzzfyrw.common.thread;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public interface IConcurrentHandler {

    void asyncExecute(Runnable r);
    void cancel(Runnable r);
    <V> V submit(Callable<V> task) throws ExecutionException, InterruptedException;
    <V> List<V> invokeAll(List<? extends Callable<V>> tasks) throws InterruptedException, ExecutionException;

}
