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

    /**
     * 黑名单拦截
     */
    public static final Integer BLACK_IP_FILTER = 1;


    /**
     * 黑名单
     */
    public static final String BLACK_IP_TIP = "REQUEST_BLACK_IP_TIP:";

}
