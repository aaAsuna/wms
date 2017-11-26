package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmployeeQueryObject extends QueryObject{
    private String keyword;
    private Long deptId = -1L;
}
