package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.OrderBill;
import org.ricardo.wms.domain.OrderBillItem;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.OrderBillQueryObject;
import org.ricardo.wms.service.IOrderBillService;
import org.ricardo.wms.service.ISupplierService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orderBill")
public class OrderBillController extends BaseController{
    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private ISupplierService supplierService;
    @RequiredPermission("采购订单列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") OrderBillQueryObject qo){
        PageResult result = orderBillService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("suppliers",supplierService.list());
        return "orderBill/list";
    }
    @RequiredPermission("编辑采购订单")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            OrderBill orderBill = orderBillService.get(id);
            model.addAttribute("orderBill",orderBill);
        }
        model.addAttribute("suppliers",supplierService.list());
        return "orderBill/input";
    }
    @RequiredPermission("保存/更新采购订单")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(OrderBill orderBill){
        try {
            for (OrderBillItem item : orderBill.getItems()) {
                if(item.getNumber() == null) {
                    return failed("订单明细数量不能为空");
                }
            }
            if(orderBill.getId()!=null){
                orderBillService.update(orderBill);
            }else {
                orderBillService.save(orderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed("操作失败");
        }
        return success();
    }
    @RequiredPermission("删除采购订单")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            orderBillService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("审核采购订单")
    @RequestMapping("audit")
    @ResponseBody
    public JsonResult audit(Long id) {
        if (id != null) {
            orderBillService.audit(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("查看采购订单")
    @RequestMapping("view")
    public String view(Long id, Model model) {
        if (id != null) {
            model.addAttribute("orderBill", orderBillService.get(id));
        }
        return "orderBill/view";
    }
}
