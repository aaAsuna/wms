package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Department;
import org.ricardo.wms.domain.Employee;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.EmployeeQueryObject;
import org.ricardo.wms.service.IDepartmentService;
import org.ricardo.wms.service.IEmployeeService;
import org.ricardo.wms.service.IRoleService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController extends BaseController{
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;
    @RequiredPermission("员工列表")
    @RequestMapping("query")
    public String query(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        List<Department> depts = departmentService.list();
        model.addAttribute("depts", depts);
        PageResult result = employeeService.query(qo);
        model.addAttribute("result", result);
        return "employee/list";
    }
    @RequiredPermission("编辑员工")
    @RequestMapping("input")
    public String input(Long id, Model model){
        List<Department> depts = departmentService.list();
        model.addAttribute("depts", depts);
        model.addAttribute("roles", roleService.list());
        if (id != null) {
            Employee employee = employeeService.get(id);
            model.addAttribute("employee", employee);
        }
        return "employee/input";
    }
    @RequiredPermission("更新/保存员工")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee,Long[] ids){
        try {
            if(employee.getId()!=null){
                employeeService.update(employee,ids);
            }else {
                employeeService.save(employee,ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除员工")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
            return success();
        }
        return failed();
    }
    @RequiredPermission("批量删除员工")
    @RequestMapping("batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids) {
        try {
            if (ids != null) {
                employeeService.batchDelete(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
}


