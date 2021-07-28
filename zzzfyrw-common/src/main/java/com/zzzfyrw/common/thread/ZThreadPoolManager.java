package com.zzzfyrw.common.thread;

import com.zzzfyrw.common.thread.abs.AbstractThreadPool;
import com.zzzfyrw.common.thread.factory.NameThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ZThreadPoolManager extends AbstractThreadPool{

    public enum ThreadPoolHelper{
        INSTANCE;
        private ZThreadPoolManager zThreadPoolManager;
        ThreadPoolHelper(){this.zThreadPoolManager = new ZThreadPoolManager();}
        public ZThreadPoolManager getInstance(){return zThreadPoolManager;}
    }

    public static ZThreadPoolManager getInstance(){return ThreadPoolHelper.INSTANCE.getInstance();}


    public ZThreadPoolManager(){
        setDefaultThreadFactory(new NameThreadFactory("core"));
        init();
    }


}
