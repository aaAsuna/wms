package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends BaseAuditQueryObject {
    private Long depotId = -1L;
    private Long clientId = -1L;

}
