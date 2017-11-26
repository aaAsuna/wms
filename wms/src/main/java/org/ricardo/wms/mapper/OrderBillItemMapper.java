package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {

    int insert(OrderBillItem record);

    void deleteByBillId(Long id);
}
