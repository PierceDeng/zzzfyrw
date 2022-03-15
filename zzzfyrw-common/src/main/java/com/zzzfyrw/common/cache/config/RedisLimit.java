package com.zzzfyrw.common.cache.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisLimit {

    String name() default "";

    String key() default "";

    String prefix() default "";

    //一定时间范围内
    int period();
    //可以允许的请求次数
    int count();
    
    LimitType limitType() default LimitType.CUSTOMER;



}
