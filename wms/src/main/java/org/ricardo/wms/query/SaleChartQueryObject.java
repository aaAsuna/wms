package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class SaleChartQueryObject extends BaseAuditQueryObject {
    private String keyword;
    private Long clientId = -1L;
    private Long brandId = -1L;
    private String groupBy = "sm.name" ;

    public static final Map<String, Object> groupByTypes = new LinkedHashMap<>();

    static {
        groupByTypes.put("sm.name", "销售人员");
        groupByTypes.put("p.name", "商品名称");
        groupByTypes.put("c.name", "客户");
        groupByTypes.put("p.brandName", "商品品牌");
        groupByTypes.put("DATE_FORMAT(sa.vdate,'%Y-%m')", "销售日期(月)");
        groupByTypes.put("DATE_FORMAT(sa.vdate,'%Y-%m-%d')", "销售日期(日)");
    }
    public void setKeyword(String keyword) {
        this.keyword = empty2null(keyword);
    }
}
