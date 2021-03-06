package cn.itcast.core.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.pojo.product.Brand;
import cn.itcast.core.pojo.product.Color;
import cn.itcast.core.pojo.product.Product;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.ColorService;
import cn.itcast.core.service.product.ProductService;

/**
 * 商品管理
 * @author lx
 *
 */
@Controller
public class ProductController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	//商品管理之列表查询  
	@RequestMapping(value = "/product/list.do")
	public String list(Integer pageNo,String name,Long brandId,Boolean isShow, Model model){
		//品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		//通过上面的条件  及 当前页   查询分页对象
		Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("brands", brands);
		model.addAttribute("name", name);
		model.addAttribute("brandId", brandId);
		model.addAttribute("isShow", isShow);
		
		return "product/list";
	}
	
	@Autowired
	private ColorService colorService;
	
	//去商品添加页面
	@RequestMapping(value = "/product/toAdd.do")
	public String toAdd(Model model){
		//品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		//查询颜色的结果集
		List<Color> colors = colorService.selectColors();
		model.addAttribute("brands", brands);
		model.addAttribute("colors", colors);
		return "product/add";
	}
	
	// 保存商品
	@RequestMapping(value = "/product/add.do")
	public String add(Product product){
		//保存
		productService.insertProduct(product);
		
		return "redirect:/product/list.do";
	}

	//商品上架
	@RequestMapping(value = "/brand/isShow.do")
	public String isShow(Long[] ids,Model model){
		//上架
		productService.isShow(ids);
		return "redirect:/product/list.do";
	}
	
	//商品的下架
	@RequestMapping(value = "/brand/isHide.do")
	public String isHide(Long[] ids,Model model){
		//下架
		productService.isHide(ids);
		return "redirect:/product/list.do";
	}
	
}
