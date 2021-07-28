package com.zzzfyrw.common.annotation.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZLog {

    String value() default "zlog";

    String type() default "type";

    boolean personal() default false;

    int expireTime() default 3 * 60;

    TimeUnit unit() default TimeUnit.SECONDS;

}
