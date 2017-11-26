package org.ricardo.wms.mapper;

import org.apache.ibatis.annotations.Param;
import org.ricardo.wms.domain.ProductStock;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface ProductStockMapper {

    int insert(ProductStock record);

    int updateByPrimaryKey(ProductStock record);

    ProductStock selectByProductIdAndDepotId(@Param("productId") Long productId, @Param("depotId") Long depotId);

    int queryForCount(QueryObject qo);

    List<ProductStock> queryForList(QueryObject qo);
}