package org.ricardo.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Brand extends BaseDomain {
    private String name;      //品牌名称
    private String sn;        //品牌代码

}