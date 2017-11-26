package org.ricardo.wms.query;

import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 5;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }

    protected String empty2null(String str) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }
}
