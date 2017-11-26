package org.ricardo.wms.service.impl;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Permission;
import org.ricardo.wms.mapper.PermissionMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IPermissionService;
import org.ricardo.wms.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext ctx;
    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Permission record) {
        permissionMapper.insert(record);
    }


    @Override
    public List<Permission> list() {
        return permissionMapper.selectAll();
    }


    @Override
    public PageResult query(QueryObject qo) {
        int rows = permissionMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = permissionMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void reload() {
        //查询数据库中存在的权限
        List<String> allExpression = permissionMapper.selectAllExpression();
        //从容器中获取所有contrller中的方法
        Map<String, Object> ctrls = ctx.getBeansWithAnnotation(Controller.class);
        for (Object ctrl : ctrls.values()) {
            Method[] methods = ctrl.getClass().getMethods();
            //遍历所有方法,判断是否贴有权限注解,如果没有,就不管
            for (Method m : methods) {
                if(m.isAnnotationPresent(RequiredPermission.class)){
                    //获取注解
                    RequiredPermission anno = m.getAnnotation(RequiredPermission.class);
                    //如果有,创建权限对象,并保存到数据库
                    String expression = PermissionUtil.buildExpreesion(m);
                    if(!allExpression.contains(expression)){
                        Permission p = new Permission();
                        p.setName(anno.value());
                        p.setExpression(expression);
                        permissionMapper.insert(p);
                    }

                }
            }
        }
    }


}