package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.Product;
import org.ricardo.wms.mapper.ProductMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void delete(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Product record) {
        productMapper.insert(record);
    }

    @Override
    public Product get(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> list() {
        return productMapper.selectAll();
    }

    @Override
    public void update(Product record) {
        productMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = productMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<Product> data = productMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void batchDelete(Long[] ids) {
        productMapper.batchDelete(ids);
    }
}