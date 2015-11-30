package com.imethod.sites.web.sys.auth;

import com.imethod.core.util.IdentitieTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.User;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * time : 15/10/22.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class UserContent {

    public static final ThreadLocal<LUser> userThreadLocal = new ThreadLocal<>();

    public static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();


    /**
     * 获取当前的request对象
     *
     * @return
     */
    public static final HttpServletRequest getRequest() {
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }

    /**
     * 获取当前response对象
     *
     * @return
     */
    public static final HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseThreadLocal.set(response);
    }

    public static void setLUser(LUser user) {
        userThreadLocal.set(user);
    }


    public static LUser getLUser() {
        return userThreadLocal.get();
    }


    public static void saveLUser(LUser user) {
        setLUser(user);
    }


    /**
     * 清除登录上下文环境
     */
    public static void clear() {
        userThreadLocal.set(null);
        requestThreadLocal.set(null);
        responseThreadLocal.set(null);
    }


}
