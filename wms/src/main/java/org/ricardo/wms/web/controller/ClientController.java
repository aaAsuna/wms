package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Client;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IClientService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("client")
public class ClientController extends BaseController{
    @Autowired
    private IClientService clientService;
    @RequiredPermission("客户列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = clientService.query(qo);
        model.addAttribute("result",result);
        return "client/list";
    }
    @RequiredPermission("编辑客户")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Client client = clientService.get(id);
            model.addAttribute("client",client);
        }
        return "client/input";
    }
    @RequiredPermission("保存/更新客户")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Client client){
        try {
            if(client.getId()!=null){
                clientService.update(client);
            }else {
                clientService.save(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除客户")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            clientService.delete(id);
            return success();
        }
        return failed();
    }
}
