package org.ricardo.wms.web.controller;

import org.ricardo.wms.service.IEmployeeService;
import org.ricardo.wms.util.JsonResult;
import org.ricardo.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController extends BaseController{
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login (String username,String password) {
        try {
            employeeService.login(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("logout")
    public String logout() {
        UserContext.setCurrentUser(null);
        return "redirect:login.html";
    }
}
