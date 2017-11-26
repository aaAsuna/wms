package org.ricardo.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStock extends BaseDomain {

    private BigDecimal price; //库存价格
    private BigDecimal storeNumber; //库存数量
    private BigDecimal amount; //库存总额

    private Depot depot; //仓库
    private Product product; //库存货品
}
