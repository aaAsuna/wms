package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class OrderCharQueryObject extends BaseAuditQueryObject{

    private String keyword;

    private Long supplierId = -1L;

    private Long brandId = -1l;

    private String groupBy = "iu.name";

    public static final Map<String, Object> groupByTypes = new LinkedHashMap<>();

    static {
        groupByTypes.put("iu.name", "订货人员");
        groupByTypes.put("p.name", "商品名称");
        groupByTypes.put("s.name", "供应商");
        groupByTypes.put("p.brandName", "商品品牌");
        groupByTypes.put("DATE_FORMAT(bill.vdate,'%Y-%m')", "订货日期(月)");
        groupByTypes.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')", "订货日期(日)");
    }
    public void setKeyword(String keyword) {
        this.keyword = empty2null(keyword);
    }
}
