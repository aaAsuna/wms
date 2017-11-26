package org.ricardo.wms.web.interceptpr;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.exception.SystemException;
import org.ricardo.wms.util.PermissionUtil;
import org.ricardo.wms.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SercurityInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前用户,判断是否为超级管理员
        Employee user = UserContext.getCurrentUser();
        //如果是超级管理员,放行
        if(user.getAdmin()){
            return true;
        }
        //判断当前访问的方法是否贴有权限注解
        HandlerMethod hm = (HandlerMethod) handler;
        if(hm.getMethodAnnotation(RequiredPermission.class) == null) {
            //如果没有:放行
            return true;
        }
        //获取到方法对应的controller类,获取类名拼接:获取方法名
        String exp = PermissionUtil.buildExpreesion(hm.getMethod());
        //String exp = hm.getBean().getClass().getName() + ":" + hm.getMethod().getName();
        //得到用户所有的权限列表,判断当前方法的表达式是否在列表中
        List<String> exps = UserContext.getCurrentPerms();
        if(exps.contains(exp)){
            //如果在:放行
            return true;
        }
        throw new SystemException("没有权限!");
    }
}
