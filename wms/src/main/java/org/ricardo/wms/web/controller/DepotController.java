package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Depot;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IDepotService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("depot")
public class DepotController extends BaseController{
    @Autowired
    private IDepotService depotService;
    @RequiredPermission("仓库列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = depotService.query(qo);
        model.addAttribute("result",result);
        return "depot/list";
    }
    @RequiredPermission("编辑仓库")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Depot depot = depotService.get(id);
            model.addAttribute("depot",depot);
        }
        return "depot/input";
    }
    @RequiredPermission("保存/更新仓库")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Depot depot){
        try {
            if(depot.getId()!=null){
                depotService.update(depot);
            }else {
                depotService.save(depot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除仓库")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            depotService.delete(id);
            return success();
        }
        return failed();
    }
}
