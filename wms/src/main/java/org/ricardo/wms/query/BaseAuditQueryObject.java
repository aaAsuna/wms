package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.ricardo.wms.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class BaseAuditQueryObject extends QueryObject{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer status = -1;

    public void setEndDate (Date date) {
        this.endDate = DateUtil.getEndDate(date);
    }
}
