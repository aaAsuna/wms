package org.ricardo.wms.service;

import org.ricardo.wms.domain.OrderBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

public interface IOrderBillService {
    void delete(Long id);

    void save(OrderBill record);

    OrderBill get(Long id);


    void update(OrderBill record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
