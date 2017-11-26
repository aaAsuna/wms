package org.ricardo.wms.service;

import org.ricardo.wms.domain.Department;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void delete(Long id);

    void save(Department record);

    Department get(Long id);

    List<Department> list();

    void update(Department record);

    PageResult query(QueryObject qo);

    void batchDelete(Long[] ids);
}
