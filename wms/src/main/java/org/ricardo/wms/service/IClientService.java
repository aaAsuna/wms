package org.ricardo.wms.service;

import org.ricardo.wms.domain.Client;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface IClientService {
    void delete(Long id);

    void save(Client record);

    Client get(Long id);

    List<Client> list();

    void update(Client record);

    PageResult query(QueryObject qo);

}
