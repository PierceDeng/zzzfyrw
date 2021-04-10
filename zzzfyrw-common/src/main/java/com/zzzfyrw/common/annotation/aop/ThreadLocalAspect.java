package com.zzzfyrw.common.annotation.aop;

import com.zzzfyrw.common.thread.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(99)
public class ThreadLocalAspect {

    @Pointcut(value = "execution(* com.zzzfyrw.*.controller.*Controller.*(..))")
    public void saveThreadLocalMap() {}

    @Around(value = "saveThreadLocalMap()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around");
        return joinPoint.proceed();
    }

    @After(value = "saveThreadLocalMap()")
    public void after(JoinPoint jd) {
        System.out.println("after");
        ThreadLocalUtil.removeAll();
    }

}
