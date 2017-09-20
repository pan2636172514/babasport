package cn.itcast.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.pojo.product.Brand;
import cn.itcast.core.service.product.BrandService;

/**
 * 品牌管理
 * @author lx
 *
 */
@Controller
public class BrandController {

	
	@Autowired
	private BrandService brandService;
	//品牌管理 之列表查询
	@RequestMapping(value = "/brand/list.do")
	public String list(Integer pageNo,String name,Integer isDisplay,Model model){
		//通过品牌名称及是否可见 查询品牌结果集
//		List<Brand> brands = brandService.selectBrandListByQuery(name, isDisplay);
		//通过品牌名称及是否可见 当前页 查询分页对象
		Pagination pagination = brandService.selectPaginationByQuery(pageNo, name, isDisplay);//Shift + Ctrl + L
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay);
		
		return "brand/list";
	}
	//去修改页面
	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(Long id,Model model){
		Brand brand = brandService.selectBrandById(id);
		model.addAttribute("brand", brand);
		return "brand/edit";
	}
	//品牌管理之  提交修改
	@RequestMapping(value = "/brand/edit.do")
	public String edit(Brand brand){
		//修改
		brandService.updateBrandById(brand);
		return "redirect:/brand/list.do";
	}
	//删除 批量
	@RequestMapping(value = "/brand/deletes.do")
	public String deletes(Long[] ids,Model model){
		//批量删除
		brandService.deletes(ids);
		return "forward:/brand/list.do";
	}
}
