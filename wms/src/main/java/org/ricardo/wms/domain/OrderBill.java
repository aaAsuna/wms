package org.ricardo.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderBill extends BaseAuditDomain {


    private String sn;//订单编号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;//业务时间

    private BigDecimal totalAmount;//总金额
    private BigDecimal totalNumber;//总数量


    private Supplier supplier; //供应商

    private List<OrderBillItem> items = new ArrayList<>();//订单明细列表


}
