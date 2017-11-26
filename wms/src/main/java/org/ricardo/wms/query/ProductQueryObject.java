package org.ricardo.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject{
    private String keyword;
    private Long brandId;

    public void setKeyword(String keyword) {
        this.keyword = empty2null(keyword);
    }
}
