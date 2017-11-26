package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.Department;
import org.ricardo.wms.mapper.DepartmentMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Department record) {
        departmentMapper.insert(record);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> list() {
        return departmentMapper.selectAll();
    }

    @Override
    public void update(Department record) {
        departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = departmentMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Department> data = departmentMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void batchDelete(Long[] ids) {
       departmentMapper.batchDelete(ids);
    }
}