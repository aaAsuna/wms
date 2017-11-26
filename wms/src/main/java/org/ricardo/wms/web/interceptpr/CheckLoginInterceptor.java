package org.ricardo.wms.web.interceptpr;

import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.util.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取到session中的对象,判断是否为空
        Employee user = UserContext.getCurrentUser();
        //如果不为空,放行
        if(user != null) {
            return true;
        }
        //如果为空,返回false,重定向到login页面
        response.sendRedirect("/login.html");
        return false;
    }
}
