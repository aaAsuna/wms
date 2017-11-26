package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {

    int insert(StockOutcomeBillItem record);

    void deleteByBillId(Long id);
}
