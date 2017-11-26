package org.ricardo.wms.service;

import org.ricardo.wms.domain.Product;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IProductService {
    void delete(Long id);

    void save(Product record);

    Product get(Long id);

    List<Product> list();

    void update(Product record);

    PageResult query(QueryObject qo);

    void batchDelete(Long[] ids);
}
