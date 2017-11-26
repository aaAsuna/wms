package org.ricardo.wms.service;

import org.ricardo.wms.domain.StockOutcomeBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

public interface IStockOutcomeBillService {
    void delete(Long id);

    void save(StockOutcomeBill record);

    StockOutcomeBill get(Long id);


    void update(StockOutcomeBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
