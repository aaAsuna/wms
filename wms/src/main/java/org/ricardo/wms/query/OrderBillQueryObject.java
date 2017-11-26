package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderBillQueryObject extends BaseAuditQueryObject {

    private Long supplierId = -1L;

}
