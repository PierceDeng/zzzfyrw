package com.zzzfyrw.zzzfyrw.common.thread.abs;

import java.util.concurrent.*;

public abstract class AbstractThreadPool {

    private static final int CPU_COUNT              = Runtime.getRuntime().availableProcessors();
    //最大线程数 推荐公式 CPU核心数 + 1
    private static int CORE_POOL_SIZE               = CPU_COUNT + 1;
    //核心线程数 推荐公式 CPU核心数 * 2 + 1
    private static int MAX_POOL_SIZE                = CPU_COUNT * 2 + 1;
    //等待时间
    private static int KEEP_ALIVE                   = 5;
    //队列数
    private static int WAIT_WORK_QUEUE_SIZE         = 1024;
    //线程池对象
    private ThreadPoolExecutor executor;
    //线程工厂
    private ThreadFactory defaultThreadFactory      = Executors.defaultThreadFactory();
    //丢失策略
    private RejectedExecutionHandler defaultRejectedHandler = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * corePoolSize:核心线程数
     * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
     * keepAliveTime：非核心线程闲置时间超时时长
     * unit：keepAliveTime的单位
     * workQueue：等待队列，存储还未执行的任务
     * threadFactory：线程创建的工厂
     * handler：异常处理机制
     *
     */
    public void init(){
        if(executor!=null)return;
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(WAIT_WORK_QUEUE_SIZE),
                defaultThreadFactory,
                defaultRejectedHandler);
    }

    public void setDefaultThreadFactory(ThreadFactory defaultThreadFactory) {
        this.defaultThreadFactory = defaultThreadFactory;
    }

    public void setDefaultRejectedHandler(RejectedExecutionHandler defaultRejectedHandler) {
        this.defaultRejectedHandler = defaultRejectedHandler;
    }

    public static void setCorePoolSize(int corePoolSize) {
        CORE_POOL_SIZE = corePoolSize;
    }

    public static void setMaxPoolSize(int maxPoolSize) {
        MAX_POOL_SIZE = maxPoolSize;
    }

    public static void setKeepAlive(int keepAlive) {
        KEEP_ALIVE = keepAlive;
    }

    public static void setWaitWorkQueueSize(int waitWorkQueueSize) {
        WAIT_WORK_QUEUE_SIZE = waitWorkQueueSize;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
