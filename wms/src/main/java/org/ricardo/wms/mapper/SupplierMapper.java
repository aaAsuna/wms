package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.Supplier;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int queryForCount(QueryObject qo);

    List<Supplier> queryForList(QueryObject qo);
}