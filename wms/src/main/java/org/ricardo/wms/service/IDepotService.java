package org.ricardo.wms.service;

import org.ricardo.wms.domain.Depot;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IDepotService {
    void delete(Long id);

    void save(Depot record);

    Depot get(Long id);

    List<Depot> list();

    void update(Depot record);

    PageResult query(QueryObject qo);

}
