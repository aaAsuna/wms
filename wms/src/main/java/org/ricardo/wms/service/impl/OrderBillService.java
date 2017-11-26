package org.ricardo.wms.service.impl;

import org.ricardo.wms.domain.OrderBill;
import org.ricardo.wms.domain.OrderBillItem;
import org.ricardo.wms.mapper.OrderBillItemMapper;
import org.ricardo.wms.mapper.OrderBillMapper;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IOrderBillService;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class OrderBillService implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;
    @Override
    public void delete(Long id) {
        //删除订单对应的明细对象
        orderBillItemMapper.deleteByBillId(id);
        orderBillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(OrderBill record) {
        //1:设置录入人和录入时间
        record.setInputUser(UserContext.getCurrentUser());
        record.setInputTime(new Date());
        //避免乱输入,重设状态为未审核
        record.setStatus(OrderBill.STATUS_NORMAL);
        //2:遍历明细列表,计算总金额与总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for(OrderBillItem item : record.getItems()) {
            //3:遍历时计算明细的金额小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP));
            item.setAmount(amount);

            totalNumber = totalNumber.add(item.getNumber());
            totalAmount = totalAmount.add(amount);
        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);

        //4:保存采购订单
        orderBillMapper.insert(record);
        //5:保存明细
        for(OrderBillItem item : record.getItems()) {
            item.setBillId(record.getId());
            orderBillItemMapper.insert(item);
        }
    }

    @Override
    public OrderBill get(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(OrderBill record) {
        //从数据库中获取原来的订单对象, 判断状态为为审核才编辑
        OrderBill old = orderBillMapper.selectByPrimaryKey(record.getId());
        if(record.getStatus() == OrderBill.STATUS_NORMAL) {
            // 删除原有的明细
            orderBillItemMapper.deleteByBillId(record.getId());

            //计算总金额与总数量,并设置到对象中
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for(OrderBillItem item : record.getItems()) {
                //遍历时计算明细的金额小计
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP));
                item.setAmount(amount);
                //为明细对象设置订单id
                item.setBillId(record.getId());
                // 保存明细对象
                orderBillItemMapper.insert(item);

                totalNumber = totalNumber.add(item.getNumber());
                totalAmount = totalAmount.add(amount);
            }
            record.setTotalAmount(totalAmount);
            record.setTotalNumber(totalNumber);
        }
        // 更新采购订单
        orderBillMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = orderBillMapper.queryForCount(qo);
        if(rows==0){
            return PageResult.EMPTY_PAGE;
        }
        List<OrderBill> data = orderBillMapper.queryForList(qo);
        return new PageResult(data,rows,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void audit(Long id) {
        // 获取到原来的订单对象,判断状态是否为未审核
        OrderBill old = orderBillMapper.selectByPrimaryKey(id);
        if(old.getStatus() == OrderBill.STATUS_NORMAL) {
            // 设置审核信息,审核人,审核时间,状态
            old.setAuditor(UserContext.getCurrentUser());
            old.setAuditTime(new Date());
            old.setStatus(OrderBill.STATUS_AUDITED);

            //修改状态
            orderBillMapper.updateStatus(old);
        }
    }

}