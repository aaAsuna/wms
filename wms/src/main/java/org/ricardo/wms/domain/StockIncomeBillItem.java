package org.ricardo.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockIncomeBillItem extends BaseDomain {

    private BigDecimal costPrice;//进货价
    private BigDecimal number; //明细数量
    private BigDecimal amount; //金额小计
    private String remark; //备注信息
    private Long billId; //订单id

    private Product product;// 商品对象
}
