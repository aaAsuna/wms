package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.StockOutcomeBill;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.StockOutcomeBillQueryObject;
import org.ricardo.wms.service.IClientService;
import org.ricardo.wms.service.IDepotService;
import org.ricardo.wms.service.IStockOutcomeBillService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockOutcomeBill")
public class StockOutcomeBillController extends BaseController{
    @Autowired
    private IStockOutcomeBillService stockOutcomeBillService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IClientService clientService;
    @RequiredPermission("出库单列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") StockOutcomeBillQueryObject qo){
        PageResult result = stockOutcomeBillService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("depots",depotService.list());
        model.addAttribute("clients",clientService.list());
        return "stockOutcomeBill/list";
    }
    @RequiredPermission("编辑出库单")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            StockOutcomeBill stockOutcomeBill = stockOutcomeBillService.get(id);
            model.addAttribute("stockOutcomeBill",stockOutcomeBill);
        }
        model.addAttribute("depots",depotService.list());
        model.addAttribute("clients",clientService.list());
        return "stockOutcomeBill/input";
    }
    @RequiredPermission("保存/更新出库单")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StockOutcomeBill stockOutcomeBill){
        try {
            if(stockOutcomeBill.getId()!=null){
                stockOutcomeBillService.update(stockOutcomeBill);
            }else {
                stockOutcomeBillService.save(stockOutcomeBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除出库单")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            stockOutcomeBillService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("审核出库单")
    @RequestMapping("audit")
    @ResponseBody
    public JsonResult audit(Long id) {
        try {
            if (id != null) {
                stockOutcomeBillService.audit(id);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("查看出库单")
    @RequestMapping("view")
    public String view(Long id, Model model) {
        if (id != null) {
            model.addAttribute("stockOutcomeBill", stockOutcomeBillService.get(id));
        }
        return "stockOutcomeBill/view";
    }
}
