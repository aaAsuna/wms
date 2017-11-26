package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Role;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IPermissionService;
import org.ricardo.wms.service.IRoleService;
import org.ricardo.wms.service.ISystemMenuService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;
    @RequiredPermission("角色列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = roleService.query(qo);
        model.addAttribute("result",result);
        return "role/list";
    }
    @RequiredPermission("编辑角色")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Role role = roleService.get(id);
            model.addAttribute("role",role);
        }
        model.addAttribute("permissions", permissionService.list());
        model.addAttribute("menus", systemMenuService.list());
        return "role/input";
    }
    @RequiredPermission("保存/更新角色")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Role role,Long[] permsIds, Long[] menuIds){
        try {
            if(role.getId()!=null){
                roleService.update(role, permsIds, menuIds);
            }else {
                roleService.save(role, permsIds, menuIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除角色")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            roleService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("批量删除角色")
    @RequestMapping("batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids) {
        try {
            if (ids != null) {
                roleService.batchDelete(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
}
