package org.ricardo.wms.service;

import org.ricardo.wms.domain.Role;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IRoleService {
    void delete(Long id);

    void save(Role record, Long[] permsIds, Long[] menuIds);

    Role get(Long id);

    List<Role> list();

    void update(Role record, Long[] permsIds, Long[] menuIds);

    PageResult query(QueryObject qo);

    void batchDelete(Long[] ids);

}
