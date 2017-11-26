package org.ricardo.wms.service;

import org.ricardo.wms.domain.StockIncomeBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

public interface IStockIncomeBillService {
    void delete(Long id);

    void save(StockIncomeBill record);

    StockIncomeBill get(Long id);


    void update(StockIncomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
