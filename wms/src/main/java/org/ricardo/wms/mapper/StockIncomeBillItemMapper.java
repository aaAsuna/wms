package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.StockIncomeBillItem;

public interface StockIncomeBillItemMapper {

    int insert(StockIncomeBillItem record);

    void deleteByBillId(Long id);
}
