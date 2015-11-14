package com.imethod.sites.web.sys.auth;

import com.imethod.core.util.StringTools;
import com.imethod.domain.Rule;
import com.imethod.domain.User;
import com.imethod.sites.web.permission.service.PermissionService;
import com.imethod.sites.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取用户
        Integer userId = 0;
        User user = userService.loadById(1);
        String url = request.getRequestURI();
        request.setAttribute(PermissionKey.login_user_cookie_key, request.getRequestURL());
        if (StringTools.isNotEmpty(request.getQueryString())) {
            url = url.concat("?").concat(request.getQueryString());
        }
        request.setAttribute(PermissionKey.login_user_cookie_key, url);
        // 装载会话中的登录上下文
        UserContent.setLUser(null);
        UserContent.setRequest(request);
        UserContent.setResponse(response);
        LUser lUser=new LUser(user);
        UserContent.saveLUser(lUser);
        // 自动获取权限
        PermissionCheck permissionCheck;
        ResponseBody responseBody;
        HandlerMethod methodHandler = null;
        boolean allowAnonymous = false;
        String menuType;
        if (handler instanceof HandlerMethod) {
            methodHandler = (HandlerMethod) handler;
            permissionCheck = methodHandler.getMethodAnnotation(PermissionCheck.class);
            responseBody = methodHandler.getMethodAnnotation(ResponseBody.class);
            menuType = "option";
            if (permissionCheck == null) {
                Object bean = methodHandler.getBean();
                menuType = "module";
                permissionCheck = bean.getClass().getAnnotation(PermissionCheck.class);
            }
            if (permissionCheck != null) {
                if (permissionCheck.value().length() >= 1 && permissionCheck.value().equalsIgnoreCase("false")) {
                    allowAnonymous = true;
                } else {
                    String menu = permissionCheck.menu();
                    List<String> ruleList = permissionService.getUserRuleStr(userId, menuType);
                    if (!ruleList.contains(menu)) {
                        return false;
                    }
                }
            }
            if (responseBody != null) {
                request.setAttribute("responseBody", "true");
            }
        }
        request.setAttribute("LUser", user);
        if (methodHandler == null) {
            return true;
        }
        if (allowAnonymous) {
            // 不要求登录验证
            return true;
        }
        return true;
        //获取用户权限
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        restUserContent();
    }

    private void restUserContent() {
        UserContent.clear();
    }
}
