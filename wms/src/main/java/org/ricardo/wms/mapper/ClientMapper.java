package org.ricardo.wms.mapper;

import org.ricardo.wms.domain.Client;
import org.ricardo.wms.query.QueryObject;

import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);

    int queryForCount(QueryObject qo);

    List<Client> queryForList(QueryObject qo);

}