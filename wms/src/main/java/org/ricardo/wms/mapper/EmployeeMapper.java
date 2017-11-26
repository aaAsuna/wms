package org.ricardo.wms.mapper;

import org.apache.ibatis.annotations.Param;
import org.ricardo.wms.domain.Department;
import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);

    int batchDelete(Long[] ids);

    void saveRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    void deleteRelation(Long employeeId);

    Employee selectByUsernameAndPassword(@Param("name") String username, @Param("password") String password);
}