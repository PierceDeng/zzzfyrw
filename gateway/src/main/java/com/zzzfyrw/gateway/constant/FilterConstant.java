package com.zzzfyrw.gateway.constant;

public class FilterConstant {

    //TODO.  gateway filter 排序数字越小，执行顺序越高 Integer.MIN最优先 Integer.MAX最后

    /**
     * Token过滤器，用于校验请求接口token正确性
     */
    public static final Integer TOKEN_FILTER = 99;

    /**
     * 请求日志过滤器，用于存储任何请求
     */
    public static final Integer LOG_FILTER = 110;

}
