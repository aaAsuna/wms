package org.ricardo.wms.service.impl;

import org.ricardo.wms.mapper.ChartMapper;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ChartService implements IChartService {
    @Autowired
    private ChartMapper chartMapper;
    @Override
    public List<Map<String, Object>> queryForOrder(QueryObject qo) {
        return chartMapper.queryForOrder(qo);
    }

    @Override
    public List<Map<String, Object>> queryForSale(QueryObject qo) {
        return chartMapper.queryForSale(qo);
    }
}
