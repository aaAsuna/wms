package org.ricardo.wms.web.controller;

import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Brand;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.QueryObject;
import org.ricardo.wms.service.IBrandService;
import org.ricardo.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("brand")
public class BrandController extends BaseController{
    @Autowired
    private IBrandService brandService;
    @RequiredPermission("商品列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") QueryObject qo){
        PageResult result = brandService.query(qo);
        model.addAttribute("result",result);
        return "brand/list";
    }
    @RequiredPermission("编辑商品")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Brand brand = brandService.get(id);
            model.addAttribute("brand",brand);
        }
        return "brand/input";
    }
    @RequiredPermission("保存/更新商品")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Brand brand){
        try {
            if(brand.getId()!=null){
                brandService.update(brand);
            }else {
                brandService.save(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
    @RequiredPermission("删除商品")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            brandService.delete(id);
            return success();
        }
        return failed();
    }
}
