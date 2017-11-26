package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockIncomeBillQueryObject extends BaseAuditQueryObject {

    private Long depotId = -1L;

}
