package cn.itcast.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面
 * 
 * @author 潘金鑫
 *
 */
@Controller
public class ProductController {

	//去首页
	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
	
}
