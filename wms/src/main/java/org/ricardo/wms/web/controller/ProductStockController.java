package org.ricardo.wms.web.controller;

import org.ricardo.wms.query.ProductStockQueryObject;
import org.ricardo.wms.service.IBrandService;
import org.ricardo.wms.service.IDepotService;
import org.ricardo.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("productStock")
public class ProductStockController extends BaseController{

    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;

    @RequestMapping("query")
    public String list(@ModelAttribute("qo")ProductStockQueryObject qo, Model model) {
        qo.setPageSize(20);
        model.addAttribute("result", productStockService.query(qo));
        model.addAttribute("brands", brandService.list());
        model.addAttribute("depots", depotService.list());
        return "productStock/list";
    }
}
