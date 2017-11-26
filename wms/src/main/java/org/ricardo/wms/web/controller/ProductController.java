package org.ricardo.wms.web.controller;

import com.alibaba.druid.util.StringUtils;
import org.ricardo.wms.annotiation.RequiredPermission;
import org.ricardo.wms.domain.Brand;
import org.ricardo.wms.domain.Product;
import org.ricardo.wms.page.PageResult;
import org.ricardo.wms.query.ProductQueryObject;
import org.ricardo.wms.service.IBrandService;
import org.ricardo.wms.service.IProductService;
import org.ricardo.wms.util.JsonResult;
import org.ricardo.wms.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ServletContext webCtx;
    @RequiredPermission("商品列表")
    @RequestMapping("query")
    public String query(Model model,@ModelAttribute("qo") ProductQueryObject qo){
        PageResult result = productService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("brands",brandService.list());
        return "product/list";
    }
    @RequiredPermission("编辑商品")
    @RequestMapping("input")
    public String input(Long id,Model model){
        if(id!=null){
            Product product = productService.get(id);
            model.addAttribute("product",product);
        }
        model.addAttribute("brands",brandService.list());
        return "product/input";
    }
    @RequiredPermission("选择商品列表")
    @RequestMapping("selectProductList")
    public String selectProductList(Model model,@ModelAttribute("qo") ProductQueryObject qo){
        PageResult result = productService.query(qo);
        model.addAttribute("result",result);
        model.addAttribute("brands",brandService.list());
        return "product/selectProductList";
    }
    @RequiredPermission("保存/更新商品")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Product product, MultipartFile pic){
        try {
            if(product.getBrandId() != null) {
                Brand brand = brandService.get(product.getBrandId());
                product.setBrandName(brand.getName());
            }
            if(pic != null) {
                //删除原来的图片
                if(product.getImagePath() != null) {
                    UploadUtil.deleteFile(webCtx, product.getImagePath());
                }
                //图片上传
                String filePath = UploadUtil.upload(pic, webCtx.getRealPath("/upload"));
                product.setImagePath(filePath);
            }
            if(product.getId()!=null){
                productService.update(product);
            }else {
                productService.save(product);
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
    public JsonResult delete(Long id, String imagePath) {
        if (id != null) {
            productService.delete(id);
            if(!StringUtils.isEmpty(imagePath)) {
                UploadUtil.deleteFile(webCtx, imagePath);
            }
            return success();
        }
        return failed();
    }

    @RequiredPermission("批量删除部门")
    @RequestMapping("batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids) {
        try {
            if (ids != null) {
                productService.batchDelete(ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success();
    }
}
