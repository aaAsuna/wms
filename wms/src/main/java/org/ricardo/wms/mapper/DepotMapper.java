package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.Department;
import org.ricardo.wms.domain.Depot;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot record);

    int queryForCount(QueryObject qo);

    List<Depot> queryForList(QueryObject qo);

}