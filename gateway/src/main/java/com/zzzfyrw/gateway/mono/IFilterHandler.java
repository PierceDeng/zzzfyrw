package com.zzzfyrw.gateway.mono;

public interface IFilterHandler {

    void before();

    void handle();

    void after();

}
