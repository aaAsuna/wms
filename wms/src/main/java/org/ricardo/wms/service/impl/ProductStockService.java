package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.*;
import org.ricardo.wms.mapper.ProductStockMapper;
import org.ricardo.wms.mapper.SaleAccountMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class ProductStockService implements IProductStockService{
    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private SaleAccountMapper saleAccountMapper;
    @Override
    public PageResult query(QueryObject qo) {
        int count = productStockMapper.queryForCount(qo);
        if(count == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> list = productStockMapper.queryForList(qo);
        return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void stockIncome(StockIncomeBill bill) {
        for (StockIncomeBillItem item : bill.getItems()) {
            //根据货品id + 仓库id 得到一条库存信息对象
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),bill.getDepot().getId());
            //判断得到的库存对象是否为空
            if(ps == null) {
                // 如果为空则新增一条明细
                ps = new ProductStock();
                ps.setAmount(item.getAmount());
                ps.setPrice(item.getCostPrice());
                ps.setStoreNumber(item.getNumber());
                ps.setDepot(bill.getDepot());
                ps.setProduct(item.getProduct());

                //保存库存明细
                productStockMapper.insert(ps);
            } else {
                //反之更新库存信息
                //原库存总金额 + 本次入库总金额
                ps.setAmount(ps.getAmount().add(item.getAmount()));
                //原库存总数量 + 本次入库总数量
                ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
                //移动加权平均: 库存总额之和 / 库存总量 = 库存价格
                ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
                //更新库存信息
                productStockMapper.updateByPrimaryKey(ps);
            }
        }
    }

    @Override
    public void stockOutcome(StockOutcomeBill bill) {
        //遍历明细,根据产品ID + 仓库ID 获取库存明细
        for (StockOutcomeBillItem item : bill.getItems()) {
            ProductStock ps = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),bill.getDepot().getId());
            if(ps == null) {
                throw new RuntimeException(item.getProduct().getName() + "货品在" + bill.getDepot().getName() + "仓库中不存在!");
            }
            if(ps.getStoreNumber().compareTo(item.getNumber()) < 0){
                throw new RuntimeException(item.getProduct().getName() + "货品在" + bill.getDepot().getName() + "仓库中数量为" + ps.getStoreNumber() + "个!");
            }
            // 库存数量大于等于销售数量
            ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));// 计算库存数量
            ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
            // 更新库存信息
            productStockMapper.updateByPrimaryKey(ps);

            //记录销售帐
            SaleAccount sa = new SaleAccount();
            sa.setVdate(new Date());
            sa.setNumber(item.getNumber());
            sa.setCostPrice(ps.getPrice());
            sa.setCostAmount(sa.getCostPrice().multiply(sa.getNumber()).setScale(2, RoundingMode.HALF_UP));
            sa.setSaleAmount(item.getAmount());
            sa.setSalePrice(item.getSalePrice());
            sa.setProduct(item.getProduct());
            sa.setSaleMan(bill.getInputUser());
            sa.setClient(bill.getClient());

            saleAccountMapper.insert(sa);
        }
    }
}
