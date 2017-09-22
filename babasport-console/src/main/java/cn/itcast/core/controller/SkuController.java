package cn.itcast.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.pojo.product.Sku;
import cn.itcast.core.service.product.SkuService;

/**
 * 库存管理
 * @author 潘金鑫
 *
 */
@Controller
public class SkuController {

	@Autowired
	private SkuService skuService;
	//去库存页面
	@RequestMapping(value = "/sku/list.do")
	public String list(Long productId , Model model){
		List<Sku> skus = skuService.selectSkuListByProduct(productId);
		model.addAttribute("skus", skus);
		return "/sku/list";
	}
	
	//修改库存
	@RequestMapping(value = "/sku/addSku.do")
	public void updateSkuById(Sku sku,HttpServletResponse response)throws Exception{
		skuService.updateSkuById(sku);
		JSONObject jo = new JSONObject();
		jo.put("message", "1");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
	
}
