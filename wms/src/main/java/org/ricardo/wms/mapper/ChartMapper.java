package org.ricardo.wms.mapper;

import org.ricardo.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ChartMapper {
    //订货报表
    List<Map<String, Object>> queryForOrder(QueryObject qo);

    //销售报表
    List<Map<String, Object>> queryForSale(QueryObject qo);

}
