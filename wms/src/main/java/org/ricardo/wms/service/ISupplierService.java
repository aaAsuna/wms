package org.ricardo.wms.service;

import org.ricardo.wms.domain.Supplier;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface ISupplierService {
    void delete(Long id);

    void save(Supplier record);

    Supplier get(Long id);

    List<Supplier> list();

    void update(Supplier record);

    PageResult query(QueryObject qo);

}
