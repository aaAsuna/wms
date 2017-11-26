package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Supplier;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.ISupplierService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("supplier")
public class SupplierController extends BaseController{
    @Autowired
    private ISupplierService supplierService;
    @RequiredPermission("供应商列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = supplierService.query(qo);
        model.addAttribute("result",result);
        return "supplier/list";
    }
    @RequiredPermission("编辑供应商")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Supplier supplier = supplierService.get(id);
            model.addAttribute("supplier",supplier);
        }
        return "supplier/input";
    }
    @RequiredPermission("保存/更新供应商")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Supplier supplier){
        try {
            if(supplier.getId()!=null){
                supplierService.update(supplier);
            }else {
                supplierService.save(supplier);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除供应商")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            supplierService.delete(id);
            return success();
        }
        return failed();
    }

}
