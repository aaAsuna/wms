package org.ricardo.wms.web.controller;

import com.alibaba.fastjson.JSON;
import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Department;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IDepartmentService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
public class DepartmentController extends BaseController{
    @Autowired
    private IDepartmentService departmentService;
    @RequiredPermission("部门列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = departmentService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("dept",JSON.toJSONString(result.getData()));
        //System.out.println(JSON.toJSONString(result.getData()));
        return "department/list";
    }
    //-===========================================
    @RequiredPermission("部门列表")
    @RequestMapping("list")
    @ResponseBody
    public String list(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = departmentService.query(qo);
        //model.addAttribute("result",result);
        model.addAttribute("dept",JSON.toJSONString(result.getData()));
        model.addAttribute("dept",JSON.toJSON(result.getData()));
        //System.out.println(JSON.toJSONString(result.getData()));
        //return "department/department";
        return JSON.toJSONString(result.getData());
    }
    @RequiredPermission("部门列表")
    @RequestMapping("department")
    public String department(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = departmentService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("dept",JSON.toJSONString(result.getData()));
        //System.out.println(JSON.toJSONString(result.getData()));
        return "department/department";
    }
    //========================================
    @RequiredPermission("编辑部门")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Department department = departmentService.get(id);
            model.addAttribute("department",department);
        }
        return "department/input";
    }
    @RequiredPermission("保存/更新部门")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Department department){
        try {
            if(department.getId()!=null){
                departmentService.update(department);
            }else {
                departmentService.save(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除部门")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                departmentService.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("批量删除部门")
    @RequestMapping("batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids) {
        try {
            if (ids != null) {
                departmentService.batchDelete(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
}
