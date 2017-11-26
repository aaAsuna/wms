package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.StockOutcomeBill;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface StockOutcomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill record);

    int queryForCount(QueryObject qo);

    List<StockOutcomeBill> queryForList(QueryObject qo);

    void updateStatus(StockOutcomeBill old);
}