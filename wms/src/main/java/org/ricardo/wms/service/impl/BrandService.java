package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.Brand;
import org.ricardo.wms.mapper.BrandMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void delete(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Brand record) {
        brandMapper.insert(record);
    }

    @Override
    public Brand get(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> list() {
        return brandMapper.selectAll();
    }

    @Override
    public void update(Brand record) {
        brandMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = brandMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Brand> data = brandMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

}