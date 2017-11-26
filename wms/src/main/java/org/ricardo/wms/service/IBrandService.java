package org.ricardo.wms.service;

import org.ricardo.wms.domain.Brand;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IBrandService {
    void delete(Long id);

    void save(Brand record);

    Brand get(Long id);

    List<Brand> list();

    void update(Brand record);

    PageResult query(QueryObject qo);

}
