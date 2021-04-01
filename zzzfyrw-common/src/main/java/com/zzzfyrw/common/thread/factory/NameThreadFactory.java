package com.zzzfyrw.common.thread.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final String name;

    public NameThreadFactory(String name){
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.name = name.concat("-thread-pool");
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group,r,name+'-'+atomicInteger.getAndIncrement());
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

}
