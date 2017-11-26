package org.ricardo.wms.mapper;

import org.apache.ibatis.annotations.Param;
import org.ricardo.wms.domain.Role;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int queryForCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);

    int batchDelete(Long[] ids);

    void saveRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    void deleteMenuRelation(Long roleId);

    void saveMenuRelation(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}