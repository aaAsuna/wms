package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.Permission;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int queryForCount(QueryObject qo);

    List<Permission> queryForList(QueryObject qo);

    List<String> selectAllExpression();

    List<String> selectAllExpressionByEmployeeId(Long employeeId);


}