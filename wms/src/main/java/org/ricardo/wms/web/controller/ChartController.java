package org.ricardo.wms.web.controller;

import com.alibaba.fastjson.JSON;
import org.ricardo.wms.query.OrderCharQueryObject;
import org.ricardo.wms.query.SaleChartQueryObject;
import org.ricardo.wms.service.IBrandService;
import org.ricardo.wms.service.IChartService;
import org.ricardo.wms.service.IClientService;
import org.ricardo.wms.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private IChartService chartService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IClientService clientService;

    @RequestMapping("order")
    public String order(@ModelAttribute("qo")OrderCharQueryObject qo,Model model) {
        model.addAttribute("orderList", chartService.queryForOrder(qo));
        model.addAttribute("suppliers", supplierService.list());
        model.addAttribute("brands", brandService.list());
        model.addAttribute("groupTypes", OrderCharQueryObject.groupByTypes);
        return "chart/order";
    }

    @RequestMapping("sale")
    public String sale(@ModelAttribute("qo")SaleChartQueryObject qo, Model model) {
        model.addAttribute("saleList", chartService.queryForSale(qo));
        model.addAttribute("clients", clientService.list());
        model.addAttribute("brands", brandService.list());
        model.addAttribute("groupTypes", SaleChartQueryObject.groupByTypes);
        return "chart/sale";
    }
    //销售报表-柱状图
    @RequestMapping("saleByBar")
    public String saleChartByBar(@ModelAttribute("qo")SaleChartQueryObject qo, Model model) {

        List<Map<String, Object>> list = chartService.queryForSale(qo);

        List<Object> groupByList = new ArrayList<>();//封装分组条件,表示横坐标数据

        List<Object> totalAmount = new ArrayList<>(); //封装销售总额,表示纵坐标数据

        for (Map<String,Object> map : list) {
            groupByList.add(map.get("groupType"));
            totalAmount.add(map.get("totalAmount"));
        }

        model.addAttribute("groupType",SaleChartQueryObject.groupByTypes.get(qo.getGroupBy()));//按照什么分组

        model.addAttribute("groupByList", JSON.toJSONString(groupByList));
        model.addAttribute("totalAmount", JSON.toJSONString(totalAmount));
        return "chart/saleChartByBar";
    }

    //销售报表-饼图
    @RequestMapping("saleByPie")
    public String saleChartByPie(@ModelAttribute("qo")SaleChartQueryObject qo, Model model) {

        List<Map<String, Object>> list = chartService.queryForSale(qo);

        List<Map<String, Object>> data = new ArrayList<>();//存储需要共享的数据

        List<Object> groupByList = new ArrayList<>();

        BigDecimal max = BigDecimal.ZERO;//最大的销售总额
        for (Map<String,Object> row : list) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
            BigDecimal amount = (BigDecimal) row.get("totalAmount");
            groupByList.add(row.get("groupType"));
            map.put("name", row.get("groupType"));
            map.put("value", row.get("totalAmount"));

            if(amount.compareTo(max) >= 0) {
                max = amount;
            }
        }
        model.addAttribute("data", JSON.toJSONString(data));
        model.addAttribute("groupByList", JSON.toJSONString(groupByList));
        model.addAttribute("groupType",SaleChartQueryObject.groupByTypes.get(qo.getGroupBy()));//按照什么分组
        model.addAttribute("max",max);//按照什么分组

        return "chart/saleChartByPie";
    }
}
