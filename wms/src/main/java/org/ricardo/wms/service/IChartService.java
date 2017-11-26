package org.ricardo.wms.service;

import org.ricardo.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface IChartService {
    List<Map<String, Object>> queryForOrder(QueryObject qo);

    List<Map<String, Object>> queryForSale(QueryObject qo);
}
