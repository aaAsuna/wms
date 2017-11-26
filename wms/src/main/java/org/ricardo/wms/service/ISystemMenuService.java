package org.ricardo.wms.service;

import org.ricardo.wms.domain.SystemMenu;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
    void delete(Long id);

    void save(SystemMenu record, Long parentId);

    SystemMenu get(Long id);

    List<SystemMenu> list();

    void update(SystemMenu record, Long parentId);

    PageResult query(QueryObject qo);

    List<Map<String, Object>> queryParentMenus(SystemMenu parent);

    List<Map<String, Object>> loadMenusByParentSn(String parentSn);

    List<Map<String, Object>> loadMenusByParentSnAndEmpId(String parentSn,Long EmpId);
}
