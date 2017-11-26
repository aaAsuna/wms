package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.OrderBill;
import org.ricardo.wms.domain.StockIncomeBill;
import org.ricardo.wms.domain.StockIncomeBillItem;
import org.ricardo.wms.mapper.StockIncomeBillItemMapper;
import org.ricardo.wms.mapper.StockIncomeBillMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IProductStockService;
import org.ricardo.wms.service.IStockIncomeBillService;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockIncomeBillService implements IStockIncomeBillService {
    @Autowired
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Autowired
    private StockIncomeBillItemMapper stockIncomeBillItemMapper;
    @Autowired
    private IProductStockService productStockService;
    @Override
    public void delete(Long id) {
        //删除订单对应的明细对象
        stockIncomeBillMapper.deleteByPrimaryKey(id);
        stockIncomeBillItemMapper.deleteByBillId(id);
    }

    @Override
    public void save(StockIncomeBill record) {
        //1:设置录入人和录入时间
        record.setInputUser(UserContext.getCurrentUser());
        record.setInputTime(new Date());
        //避免乱输入,重设状态为未审核
        record.setStatus(OrderBill.STATUS_NORMAL);

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        //2:遍历明细列表,计算总金额与总数量
        for (StockIncomeBillItem item : record.getItems()) {
            //3:遍历时计算明细的金额小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);

            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(item.getNumber());

        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);

        //4:保存采购订单
        stockIncomeBillMapper.insert(record);
        //5:保存明细对象
        for (StockIncomeBillItem item : record.getItems()) {
            //为明细对象设置订单id
            item.setBillId(record.getId());
            stockIncomeBillItemMapper.insert(item);
        }
    }
    @Override
    public StockIncomeBill get(Long id) {
        return stockIncomeBillMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(StockIncomeBill record) {
        //从数据库中获取原来的订单对象, 判断状态为为审核才编辑
        StockIncomeBill old = stockIncomeBillMapper.selectByPrimaryKey(record.getId());
        if(record.getStatus() == StockIncomeBill.STATUS_NORMAL) {
            // 删除原有的明细
            stockIncomeBillItemMapper.deleteByBillId(record.getId());

            //计算总金额与总数量,并设置到对象中
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for(StockIncomeBillItem item : record.getItems()) {
                //遍历时计算明细的金额小计
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP));
                item.setAmount(amount);
                //为明细对象设置订单id
                item.setBillId(record.getId());
                // 保存明细对象
                stockIncomeBillItemMapper.insert(item);

                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(amount);
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);
        }
        // 更新采购订单
        stockIncomeBillMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = stockIncomeBillMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockIncomeBill> data = stockIncomeBillMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void audit(Long id) {
        // 获取到原来的订单对象,判断状态是否为未审核
        StockIncomeBill bill = stockIncomeBillMapper.selectByPrimaryKey(id);
        if(bill.getStatus()== StockIncomeBill.STATUS_NORMAL){
            // 设置审核信息,审核人,审核时间,状态
            bill.setAuditTime(new Date());
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setStatus(StockIncomeBill.STATUS_AUDITED);
            //========================================
            //修改库存
            productStockService.stockIncome(bill);

            //修改状态
            stockIncomeBillMapper.updateStatus(bill);
        }
    }

}