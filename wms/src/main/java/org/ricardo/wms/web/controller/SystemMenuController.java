package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.domain.SystemMenu;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.SystemMenuQueryObject;
import org.ricardo.wms.service.ISystemMenuService;
import org.ricardo.wms.util.JsonResult;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("systemMenu")
public class SystemMenuController extends BaseController {
    @Autowired
    private ISystemMenuService systemMenuService;

    @RequiredPermission("系统菜单列表")
    @RequestMapping("query")
    public String query(Model model, @ModelAttribute("qo") SystemMenuQueryObject qo) {
        if (qo.getParentId() != null) {
            SystemMenu parent = systemMenuService.get(qo.getParentId());
            model.addAttribute("menus", systemMenuService.queryParentMenus(parent));
        }
        PageResult result = systemMenuService.query(qo);
        model.addAttribute("result", result);
        return "systemMenu/list";
    }

    @RequiredPermission("编辑系统菜单")
    @RequestMapping("input")
    public String input(Long id, Long parentId, Model model) {
        if (parentId == null) {
            model.addAttribute("parentName", "根目录");
        } else {
            SystemMenu menu = systemMenuService.get(parentId);
            model.addAttribute("parentName", menu.getName());
            model.addAttribute("parentId", menu.getId());
        }
        if (id != null) {
            SystemMenu systemMenu = systemMenuService.get(id);
            model.addAttribute("systemMenu", systemMenu);
        }
        return "systemMenu/input";
    }

    @RequiredPermission("保存/更新系统菜单")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemMenu systemMenu, Long parentId) {
        try {
            if (parentId != null) {
                SystemMenu parent = new SystemMenu();
                parent.setId(parentId);
                systemMenu.setParent(parent);
            }

            if (systemMenu.getId() != null) {
                systemMenuService.update(systemMenu, parentId);
            } else {
                systemMenuService.save(systemMenu, parentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }

    @RequiredPermission("删除系统菜单")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            systemMenuService.delete(id);
            return success();
        }
        return failed();
    }

    @RequestMapping("loadMenusByParentSn")
    @ResponseBody
    public List<Map<String, Object>> loadMenusByParentSn(String parentSn) {
        //实现菜单权限控制
        //1.获取当前用户,判断是否是超级管理员
        Employee user = UserContext.getCurrentUser();
        //2.如果是超级管理员,直接返回所有菜单
        if (user.getAdmin()) {
            return systemMenuService.loadMenusByParentSn(parentSn);
        }
        //3.如果不是超级管理员,根据当前用户拥有的角色查询出对应的菜单
        List<Map<String, Object>> menus = new ArrayList<>();
        menus = systemMenuService.loadMenusByParentSnAndEmpId(parentSn,user.getId());
        return menus;
    }
}