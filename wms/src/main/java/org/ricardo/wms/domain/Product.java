package org.ricardo.wms.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Product extends BaseDomain {
    private String name;        //货品名称

    private String sn;          //货品编码

    private BigDecimal costPrice;   //成本价格

    private BigDecimal salePrice;   //销售价格

    private String imagePath;       //图片路径

    private String intro;           //货品简介

    private Long brandId;           //品牌ID

    private String brandName;       //品牌名称

    public String getSmallImagePath() {
        if(imagePath != null) {
            int index = imagePath.lastIndexOf(".");
            return imagePath.substring(0, index) + "_small" + imagePath.substring(index);
        }
        return "";
    }

    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("name", this.name);
        map.put("brandName", this.brandName);
        map.put("costPrice", this.costPrice);
        map.put("salePrice", this.salePrice);
        return JSON.toJSONString(map);
    }

}