package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.OrderBill;
import org.ricardo.wms.domain.StockOutcomeBill;
import org.ricardo.wms.domain.StockOutcomeBillItem;
import org.ricardo.wms.mapper.StockOutcomeBillItemMapper;
import org.ricardo.wms.mapper.StockOutcomeBillMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IProductStockService;
import org.ricardo.wms.service.IStockOutcomeBillService;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockOutcomeBillService implements IStockOutcomeBillService {
    @Autowired
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Autowired
    private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
    @Autowired
    private IProductStockService productStockService;
    @Override
    public void delete(Long id) {
        //删除订单对应的明细对象
        stockOutcomeBillMapper.deleteByPrimaryKey(id);
        stockOutcomeBillItemMapper.deleteByBillId(id);
    }

    @Override
    public void save(StockOutcomeBill record) {
        //1:设置录入人和录入时间
        record.setInputUser(UserContext.getCurrentUser());
        record.setInputTime(new Date());
        //避免乱输入,重设状态为未审核
        record.setStatus(OrderBill.STATUS_NORMAL);

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        //2:遍历明细列表,计算总金额与总数量
        for (StockOutcomeBillItem item : record.getItems()) {
            //3:遍历时计算明细的金额小计
            BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);

            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(item.getNumber());

        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);

        //4:保存采购订单
        stockOutcomeBillMapper.insert(record);
        //5:保存明细对象
        for (StockOutcomeBillItem item : record.getItems()) {
            //为明细对象设置订单id
            item.setBillId(record.getId());
            stockOutcomeBillItemMapper.insert(item);
        }
    }
    @Override
    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(StockOutcomeBill record) {
        //从数据库中获取原来的订单对象, 判断状态为为审核才编辑
        StockOutcomeBill old = stockOutcomeBillMapper.selectByPrimaryKey(record.getId());
        if(record.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
            // 删除原有的明细
            stockOutcomeBillItemMapper.deleteByBillId(record.getId());

            //计算总金额与总数量,并设置到对象中
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for(StockOutcomeBillItem item : record.getItems()) {
                //遍历时计算明细的金额小计
                BigDecimal amount = item.getSalePrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP));
                item.setAmount(amount);
                //为明细对象设置订单id
                item.setBillId(record.getId());
                // 保存明细对象
                stockOutcomeBillItemMapper.insert(item);

                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(amount);
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);
        }
        // 更新采购订单
        stockOutcomeBillMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = stockOutcomeBillMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutcomeBill> data = stockOutcomeBillMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void audit(Long id) {
        // 获取到原来的订单对象,判断状态是否为未审核
        StockOutcomeBill bill = stockOutcomeBillMapper.selectByPrimaryKey(id);
        if(bill.getStatus()== StockOutcomeBill.STATUS_NORMAL){
            // 设置审核信息,审核人,审核时间,状态
            bill.setAuditTime(new Date());
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setStatus(StockOutcomeBill.STATUS_AUDITED);
            //========================================
            //更新库存
            productStockService.stockOutcome(bill);

        }
        //更新状态信息
        stockOutcomeBillMapper.updateStatus(bill);
    }

}