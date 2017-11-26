package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.Role;
import org.ricardo.wms.mapper.RoleMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public void delete(Long id) {
        //先删除关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteMenuRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Role record, Long[] permsIds, Long[] menuIds) {
        roleMapper.insert(record);
        //保存新的关系
        if(permsIds!=null){
            for (Long id : permsIds) {
                roleMapper.saveRelation(record.getId(),id);
            }
        }
        if(menuIds!=null){
            for (Long id : menuIds) {
                roleMapper.saveMenuRelation(record.getId(),id);
            }
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectAll();
    }

    @Override
    public void update(Role record, Long[] permsIds, Long[] menuIds) {
        //先删除关系
        roleMapper.deleteRelation(record.getId());
        roleMapper.deleteMenuRelation(record.getId());
        roleMapper.updateByPrimaryKey(record);
        //保存新的关系
        if(permsIds!=null){
            for (Long id : permsIds) {
                roleMapper.saveRelation(record.getId(),id);
            }
        }
        if(menuIds!=null){
            for (Long id : menuIds) {
                roleMapper.saveMenuRelation(record.getId(),id);
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = roleMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = roleMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }
    @Override
    public void batchDelete(Long[] ids) {
        roleMapper.batchDelete(ids);
    }

}