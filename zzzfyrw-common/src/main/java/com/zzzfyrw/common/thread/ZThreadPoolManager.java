package com.zzzfyrw.common.thread;

import com.zzzfyrw.common.thread.abs.AbstractThreadPool;
import com.zzzfyrw.common.thread.factory.NameThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ZThreadPoolManager extends AbstractThreadPool implements IConcurrentHandler {

    public enum ThreadPoolHelper{
        INSTANCE;
        private ZThreadPoolManager zThreadPoolManager;
        ThreadPoolHelper(){this.zThreadPoolManager=new ZThreadPoolManager();}
        public ZThreadPoolManager getInstance(){return zThreadPoolManager;}
    }

    public static ZThreadPoolManager getInstance(){return ThreadPoolHelper.INSTANCE.getInstance();}


    public ZThreadPoolManager(){
        setDefaultThreadFactory(new NameThreadFactory("core"));
        init();
    }

    @Override
    public void asyncExecute(Runnable r) {
        init();
        getExecutor().execute(r);
    }

    @Override
    public void cancel(Runnable r) {
        if(null != r){
            getExecutor().getQueue().remove(r);
        }
    }

    @Override
    public <V> V submit(Callable<V> task) throws ExecutionException, InterruptedException {
        Future<V> submit = getExecutor().submit(task);
        return submit.get();
    }

    @Override
    public <V> List<V> invokeAll(List<? extends Callable<V>> tasks) throws InterruptedException, ExecutionException {
        List<Future<V>> futureList = getExecutor().invokeAll(tasks);
        List<V> results = new ArrayList<>();
        for (Future<V> future : futureList) {
            results.add(future.get());
        }
        return results;
    }
}
