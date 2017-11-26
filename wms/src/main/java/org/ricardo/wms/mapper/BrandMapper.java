package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.Brand;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    int queryForCount(QueryObject qo);

    List<Brand> queryForList(QueryObject qo);

}