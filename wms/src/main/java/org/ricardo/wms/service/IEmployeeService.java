package org.ricardo.wms.service;


import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IEmployeeService {
    void delete(Long id);

    void save(Employee record, Long[] ids);

    Employee get(Long id);

    List<Employee> list();

    void update(Employee record, Long[] ids);

    PageResult query(QueryObject qo);

    void batchDelete(Long[] ids);

    void login(String username,String password);
}
