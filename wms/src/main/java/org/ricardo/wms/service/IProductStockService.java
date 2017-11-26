package org.ricardo.wms.service;

import org.ricardo.wms.domain.StockIncomeBill;
import org.ricardo.wms.domain.StockOutcomeBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

public interface IProductStockService {

    PageResult query(QueryObject qo);

    //入库操作
    void stockIncome(StockIncomeBill bill);

    //出库操作
    void stockOutcome(StockOutcomeBill bill);
}
