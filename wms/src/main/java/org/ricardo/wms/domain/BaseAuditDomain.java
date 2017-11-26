package org.ricardo.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class BaseAuditDomain extends BaseDomain {
    public static final int STATUS_NORMAL = 0;//未审核
    public static final int STATUS_AUDITED = 1;//已审核

    private Integer status = STATUS_NORMAL;// 订单状态

    private Date auditTime; //审核时间
    private Employee auditor; //审核人
    private Date inputTime; //录入时间
    private Employee inputUser; //制单人

    public String getDisplayStatus() {
        return status == STATUS_NORMAL? "未审核" : "已审核";
    }
}
