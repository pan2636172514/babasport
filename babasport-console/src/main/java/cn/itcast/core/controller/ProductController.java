package cn.itcast.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.pojo.product.Brand;
import cn.itcast.core.service.product.BrandService;
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
}
