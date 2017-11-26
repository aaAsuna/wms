package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.SystemMenu;
import org.ricardo.wms.mapper.SystemMenuMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SystemMenuService implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public void delete(Long id) {
        systemMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(SystemMenu record, Long parentId) {
        systemMenuMapper.insert(record);
    }

    @Override
    public SystemMenu get(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> list() {
        return systemMenuMapper.selectAll();
    }

    @Override
    public void update(SystemMenu record, Long parentId) {
        systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = systemMenuMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<SystemMenu> data = systemMenuMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public List<Map<String, Object>> queryParentMenus(SystemMenu parent) {
        //最终返回的父菜单集合
        List<Map<String, Object>> menus = new ArrayList<>();
        //判断父菜单不为空,追加一个父菜单
        while(parent != null) {
            //将当前对象转换成 map 封装结构为 {id: 1, name: "xxx"}
            Map<String ,Object> map = new HashMap<>();
            map.put("id", parent.getId());
            map.put("name", parent.getName());
            menus.add(map);

            //如果parent有值,从数据库获取该对象及其parent对象
            SystemMenu p = parent.getParent();
            if(p != null) {
                parent = systemMenuMapper.selectByPrimaryKey(p.getId());
            } else {
                //如果p为空,则结束循环
                parent = null;
            }
        }
        //转换集合 {3, 2, 1} ----> {1, 2, 3}
        Collections.reverse(menus);
        return menus;
    }

    @Override
    public List<Map<String, Object>> loadMenusByParentSn(String parentSn) {
        return systemMenuMapper.loadMenusByParentSn(parentSn);
    }

    @Override
    public List<Map<String, Object>> loadMenusByParentSnAndEmpId(String parentSn, Long EmpId) {
        List<Map<String, Object>> menus =systemMenuMapper.loadMenusByParentSnAndEmpId(parentSn, EmpId);
        return menus;
    }

}