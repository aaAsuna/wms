package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IPermissionService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController{
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = permissionService.query(qo);
        model.addAttribute("result",result);
        return "permission/list";
    }
    @RequiredPermission("删除权限")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            permissionService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("加载权限")
    @RequestMapping("reload")
    @ResponseBody
    public JsonResult reload() {
        try {
            permissionService.reload();
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
}
