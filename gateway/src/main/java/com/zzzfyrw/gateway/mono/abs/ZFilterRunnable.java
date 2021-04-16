package com.zzzfyrw.gateway.mono.abs;

import com.zzzfyrw.gateway.mono.IFilterHandler;

public abstract class ZFilterRunnable implements IFilterHandler, Runnable {

    @Override
    public void before() {
        return;
    }

    @Override
    public void after() {
        return;
    }

    @Override
    public void handle() {

    }

    @Override
    public void run() {
        this.before();
        this.handle();
        this.after();
    }
}
