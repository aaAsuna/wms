package org.ricardo.wms.page;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageResult {
    public static final PageResult EMPTY_PAGE = new PageResult(Collections.EMPTY_LIST,0,1,5);

    private int currentPage;
    private int pageSize;

    private List<?> data;
    private int totalCount;

    private int totalPage;
    private int prevPage;
    private int nextPage;

    public PageResult(List data,int totalCount,int currentPage,int pageSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
        this.totalCount = totalCount;

        this.totalPage = totalCount % pageSize==0? totalCount / pageSize : totalCount /pageSize + 1;
        this.prevPage = currentPage - 1 > 1? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }

}
