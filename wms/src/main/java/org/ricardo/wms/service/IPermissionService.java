package org.ricardo.wms.service;

import org.ricardo.wms.domain.Permission;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    void delete(Long id);

    void save(Permission record);

    List<Permission> list();

    PageResult query(QueryObject qo);

    void reload();

}
