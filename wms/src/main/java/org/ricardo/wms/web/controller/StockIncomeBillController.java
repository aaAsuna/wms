package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.StockIncomeBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.StockIncomeBillQueryObject;
import org.ricardo.wms.service.IStockIncomeBillService;
import org.ricardo.wms.service.IDepotService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockIncomeBill")
public class StockIncomeBillController extends BaseController{
    @Autowired
    private IStockIncomeBillService stockIncomeBillService;
    @Autowired
    private IDepotService depotService;
    @RequiredPermission("入库单列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") StockIncomeBillQueryObject qo){
        PageResult result = stockIncomeBillService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("depots",depotService.list());
        return "stockIncomeBill/list";
    }
    @RequiredPermission("编辑入库单")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            StockIncomeBill stockIncomeBill = stockIncomeBillService.get(id);
            model.addAttribute("stockIncomeBill",stockIncomeBill);
        }
        model.addAttribute("depots",depotService.list());
        return "stockIncomeBill/input";
    }
    @RequiredPermission("保存/更新入库单")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StockIncomeBill stockIncomeBill){
        try {
            if(stockIncomeBill.getId()!=null){
                stockIncomeBillService.update(stockIncomeBill);
            }else {
                stockIncomeBillService.save(stockIncomeBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除入库单")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            stockIncomeBillService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("审核入库单")
    @RequestMapping("audit")
    @ResponseBody
    public JsonResult audit(Long id) {
        if (id != null) {
            stockIncomeBillService.audit(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("查看入库单")
    @RequestMapping("view")
    public String view(Long id, Model model) {
        if (id != null) {
            model.addAttribute("stockIncomeBill", stockIncomeBillService.get(id));
        }
        return "stockIncomeBill/view";
    }
}
