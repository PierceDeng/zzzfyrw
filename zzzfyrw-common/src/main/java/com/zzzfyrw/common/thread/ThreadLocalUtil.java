package com.zzzfyrw.common.thread;

import com.zzzfyrw.common.thread.session.SysUserInfo;

import javax.servlet.http.HttpServletRequest;

public class ThreadLocalUtil{

    private static ThreadLocal<SysUserInfo> sysUserInfo = new ThreadLocal<>();
    private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static void removeAll(){
        sysUserInfo.remove();
        requestThreadLocal.remove();
    }

    public static SysUserInfo getUserInfo(){
        SysUserInfo userInfo = sysUserInfo.get();
        if(userInfo == null){
            return new SysUserInfo();
        }
        return userInfo;
    }

    public static void setUserInfo(SysUserInfo info){
        if(info != null) sysUserInfo.set(info);
    }

    public static HttpServletRequest getRequest(){
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request){
        requestThreadLocal.set(request);
    }


}
