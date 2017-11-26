package org.ricardo.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Department extends BaseDomain {
    private String name;
    private String sn;

}